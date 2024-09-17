package com.microservices.photoapp.api.users.ui.controllers;


import com.microservices.photoapp.api.users.service.UsersService;
import com.microservices.photoapp.api.users.shared.UserDto;
import com.microservices.photoapp.api.users.ui.model.CreateUserRequestModel;
import com.microservices.photoapp.api.users.ui.model.CreateUserResponseModel;
import com.microservices.photoapp.api.users.ui.model.UserResponseModel;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final Environment environment;
    private final UsersService usersService;
    private final ModelMapper modelMapper;

    @GetMapping("/status/check")
    @Parameter(name = "Authorization", required = true, example = "Bearer access_token")
    public String status() {
        log.info("Checking user status");
        return "Working on port: " + environment.getProperty("local.server.port") + " " + environment.getProperty("test.me");
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto createdUser = usersService.createUser(userDto);

        CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @GetMapping(value = "/{userId}")
    @Parameter(name = "Authorization", required = true, example = "Bearer access_token")
    public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId) {
        UserDto userDto = usersService.getUserByUserId(userId);
        UserResponseModel returnValue = modelMapper.map(userDto, UserResponseModel.class);
        ResponseEntity<UserResponseModel> body = ResponseEntity.status(HttpStatus.OK).body(returnValue);
        return body;
    }

}
