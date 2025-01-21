package com.project.userservice.controllers;

import com.project.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListeners {
    private final UserService userService;

    @KafkaListener(topics = {"keycloak.keycloak.user_entity"}, containerFactory = "userCdcListenerContainerFactory")
    public void syncUserEntity(String message) {
        userService.syncUserEntity(message);
    }
}
