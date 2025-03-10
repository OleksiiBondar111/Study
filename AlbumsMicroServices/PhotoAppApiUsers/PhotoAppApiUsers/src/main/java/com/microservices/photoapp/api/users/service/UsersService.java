package com.microservices.photoapp.api.users.service;

import com.microservices.photoapp.api.users.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsersService extends UserDetailsService {
    UserDto createUser(UserDto userDetails);
    UserDto getUserDetailsByEmail(String email);
    UserDto getUserByUserId(String userId, String authorization);
    List<UserDto> getAllUsers();
}
