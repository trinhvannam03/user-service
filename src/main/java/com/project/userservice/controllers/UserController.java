package com.project.userservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.userservice.dtos.MediaDTO;
import com.project.userservice.dtos.UserEntityDTO;
import com.project.userservice.services.CloudinaryService;
import com.project.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CloudinaryService cloudinaryService;
    private final ObjectMapper objectMapper;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public ResponseEntity<UserEntityDTO> getProfile(@AuthenticationPrincipal Jwt jwt) {
        try {
            String userEntityId = userService.getSubject(jwt);
            UserEntityDTO userEntityDTO = userService.getUserProfile(userEntityId);
            return ResponseEntity.ok(userEntityDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/profile")
    public ResponseEntity<UserEntityDTO> updateUserProfile(
            @AuthenticationPrincipal Jwt jwt,
            @RequestParam(required = false) MultipartFile profilePicture,
            @RequestParam(value = "profile") String profileString
    ) {
        try {
            UserEntityDTO userEntityDTO = objectMapper.readValue(profileString, UserEntityDTO.class);
            if (profilePicture != null) {
                MediaDTO mediaDTO = cloudinaryService.upload(profilePicture);
                userEntityDTO.setProfilePicture(mediaDTO.getSecureUrl());
            }
            String userEntityId = userService.getSubject(jwt);
            if (!userEntityId.equals(userEntityDTO.getUserEntityId())) {
                return ResponseEntity.badRequest().build();
            }
            userEntityDTO = userService.updateUserProfile(userEntityDTO);
            return ResponseEntity.ok(userEntityDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
