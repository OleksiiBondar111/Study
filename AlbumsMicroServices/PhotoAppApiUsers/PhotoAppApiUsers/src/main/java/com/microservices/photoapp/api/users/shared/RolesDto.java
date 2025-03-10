package com.microservices.photoapp.api.users.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolesDto {
    private Roles role;
    private List<String> authorities;
}
