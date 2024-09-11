package com.microservices.photoapp.api.users.shared;

import com.microservices.photoapp.api.users.ui.model.AlbumResponseModel;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String userId;
    private String password;
    private String encryptedPassword;
    private List<AlbumResponseModel> albums;
}
