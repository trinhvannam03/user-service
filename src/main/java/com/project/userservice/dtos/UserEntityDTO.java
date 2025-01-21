package com.project.userservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.userservice.entities.UserEntity;
import com.project.userservice.enums.AccountStatus;
import com.project.userservice.enums.AccountVisibility;
import com.project.userservice.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEntityDTO {
    private String userEntityId;
    private String username;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String fullName;
    private String profilePicture;
    private String biography;
    private boolean emailVerified;
    private Gender gender;
    private String website;
    private AccountVisibility accountVisibility;
    private AccountStatus accountStatus;

    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setUserEntityId(userEntityId);
        userEntity.setEmail(email);
        userEntity.setPhone(phone);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setEmailVerified(emailVerified);
        userEntity.setGender(gender);
        userEntity.setProfilePicture(profilePicture);
        userEntity.setBiography(biography);
        userEntity.setWebsite(website);
        userEntity.setAccountVisibility(accountVisibility);
        userEntity.setAccountStatus(accountStatus);
        return userEntity;
    }
}
