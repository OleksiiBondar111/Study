package com.microservices.photoapp.api.users.ui.model;

import com.microservices.photoapp.api.users.shared.RolesDto;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseModel {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<AlbumResponseModel> albums;
    private List<RolesDto> roles;
}
