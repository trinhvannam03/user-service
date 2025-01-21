package com.project.userservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.userservice.entities.UserEntity;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEntityCDC {
    @JsonProperty("ID")
    private String userEntityId;
    @JsonProperty("USERNAME")
    private String username;
    @JsonProperty("EMAIL")
    private String email;
    @JsonProperty("PHONE")
    private String phone;
    @JsonProperty("FIRST_NAME")
    private String firstName;
    @JsonProperty("LAST_NAME")
    private String lastName;
    @JsonProperty("EMAIL_VERIFIED")
    private boolean emailVerified;

    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserEntityId(userEntityId);
        userEntity.setUsername(username);
        userEntity.setEmail(email);
        userEntity.setPhone(phone);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setEmailVerified(emailVerified);
        return userEntity;
    }
}
