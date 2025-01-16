package com.project.userservice.controllers;

import com.project.userservice.dtos.UserEntityDTO;
import com.project.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public ResponseEntity<UserEntityDTO> getProfile(@AuthenticationPrincipal Jwt jwt) {
        try {
            String userEntityId = jwt.getClaims().get("sub").toString();
            UserEntityDTO userEntityDTO = userService.getUserProfile(userEntityId);
            return ResponseEntity.ok(userEntityDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
