package com.project.userservice.services;

import com.project.userservice.dtos.UserEntityDTO;
import com.project.userservice.entities.UserEntity;
import com.project.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntityDTO getUserProfile(String userEntityId) {
        Optional<UserEntity> userEntity = userRepository.findUserEntityByUserEntityId(userEntityId);
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            return UserEntityDTO.builder()
                    .userEntityId(user.getUserEntityId())
                    .lastName(user.getLastName())
                    .firstName(user.getFirstName())
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .phone(user.getPhone())
                    .profilePicture(user.getProfilePicture())
                    .build();
        }
        throw new RuntimeException();
    }
}
