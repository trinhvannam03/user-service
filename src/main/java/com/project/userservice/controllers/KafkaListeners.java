package com.project.userservice.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListeners {

    @KafkaListener(topics = {"keycloak.keycloak.user_entity"}, containerFactory = "userCdcListenerContainerFactory")
    public void captureUserChange(String message) {
        System.out.println(message);
    }
}
