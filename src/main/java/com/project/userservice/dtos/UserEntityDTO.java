package com.project.userservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserEntityDTO {
    private String userEntityId;
    private String username;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String biography;
}
