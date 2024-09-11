package com.microservices.photoapp.api.users.service;

import com.microservices.photoapp.api.users.data.AlbumsServiceClient;
import com.microservices.photoapp.api.users.data.UserEntity;
import com.microservices.photoapp.api.users.data.UsersRepository;
import com.microservices.photoapp.api.users.shared.UserDto;
import com.microservices.photoapp.api.users.ui.model.AlbumResponseModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ModelMapper modelMapper;

    private final AlbumsServiceClient albumsServiceClient;

    private final Environment environment;

    @Override
    public UserDto createUser(UserDto userDetails) {

        userDetails.setUserId(UUID.randomUUID().toString());
        userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

        return modelMapper.map(usersRepository.save(userEntity), UserDto.class);
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = usersRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = usersRepository.findByEmail(username);
        if (userEntity == null) throw new UsernameNotFoundException(username);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true,
                new ArrayList<>());
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = usersRepository.findByUserId(userId);
        if (userEntity == null) throw new UsernameNotFoundException("User not found");
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
        //     String albumsUrl = String.format(environment.getProperty("albums.url"), userId);
//        ResponseEntity<List<AlbumsResponseModel>> response =
//                restTemplate.exchange(albumsUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
//                });
        List<AlbumResponseModel> response = albumsServiceClient.getAlbums(userId);
        userDto.setAlbums(response);
        return userDto;
    }
}
