package com.project.userservice.entities;

import com.project.userservice.dtos.UserEntityDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
@Builder
public class UserEntity {
    @Id
    private String userEntityId;
    private String username;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String biography;
    private String profilePicture;

    @OneToMany(mappedBy = "activeUser")
    private List<UserRelationship> activeRelationships = new ArrayList<>();

    @OneToMany(mappedBy = "passiveUser")
    private List<UserRelationship> passiveRelationships = new ArrayList<>();


    public UserEntityDTO toDTO() {
        return UserEntityDTO
                .builder()
                .build();
    }
}
