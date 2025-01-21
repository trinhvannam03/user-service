package com.project.userservice.entities;

import com.project.userservice.dtos.UserEntityDTO;
import com.project.userservice.enums.AccountStatus;
import com.project.userservice.enums.AccountVisibility;
import com.project.userservice.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class UserEntity {
    @Id
    @Column(name = "user_entity_id")
    private String userEntityId;
    private String username;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String biography;
    private String profilePicture;
    private String website;
    private boolean emailVerified;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private AccountVisibility accountVisibility;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @OneToMany(mappedBy = "activeUser")
    private List<UserRelationship> activeRelationships = new ArrayList<>();

    @OneToMany(mappedBy = "passiveUser")
    private List<UserRelationship> passiveRelationships = new ArrayList<>();

    public UserEntityDTO toDTO() {
        UserEntityDTO userEntityDTO = new UserEntityDTO();
        userEntityDTO.setUserEntityId(this.userEntityId);
        userEntityDTO.setUsername(this.username);
        userEntityDTO.setEmail(this.email);
        userEntityDTO.setPhone(this.phone);
        userEntityDTO.setFirstName(this.firstName);
        userEntityDTO.setLastName(this.lastName);
        userEntityDTO.setBiography(this.biography);
        userEntityDTO.setProfilePicture(this.profilePicture);
        userEntityDTO.setEmailVerified(this.emailVerified);
        userEntityDTO.setFullName(this.firstName + " " + this.lastName);
        userEntityDTO.setGender(this.gender);
        userEntityDTO.setAccountVisibility(this.accountVisibility);
        userEntityDTO.setAccountStatus(this.accountStatus);
        userEntityDTO.setWebsite(this.website);
        return userEntityDTO;
    }
}
