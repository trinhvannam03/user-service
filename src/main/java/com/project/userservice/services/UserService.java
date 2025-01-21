package com.project.userservice.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.project.userservice.dtos.UserEntityCDC;
import com.project.userservice.dtos.UserEntityDTO;
import com.project.userservice.entities.UserEntity;
import com.project.userservice.entities.UserRelationship;
import com.project.userservice.enums.RelationshipType;
import com.project.userservice.repositories.RelationshipRepository;
import com.project.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RelationshipRepository relationshipRepository;
    private final ObjectMapper objectMapper;

    public String getSubject(Jwt jwt) {
        return jwt.getClaims().get("sub").toString();
    }

    public UserEntityDTO getUserProfile(String userEntityId) {
        Optional<UserEntity> userEntity = userRepository.findUserEntityByUserEntityId(userEntityId);
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            return user.toDTO();
        }
        throw new RuntimeException();
    }

    public UserEntityDTO updateUserProfile(UserEntityDTO userEntityDTO) {
        Optional<UserEntity> userEntity = userRepository.findUserEntityByUserEntityId(userEntityDTO.getUserEntityId());
        if (userEntity.isEmpty()) {
            throw new RuntimeException();
        }
        UserEntity user = userEntityDTO.toEntity();
        userRepository.save(user);
        return userEntityDTO;
    }

    public List<UserEntityDTO> getFollowers(String userEntityId) {
        List<UserEntityDTO> userEntities = new ArrayList<>();
        return userEntities;
    }

    public void establishRelationship(String activeUserId, String passiveUserId, RelationshipType relationshipType) {
        Optional<UserRelationship> optional = relationshipRepository.findUserRelationshipBetween(activeUserId, passiveUserId);
        Optional<UserRelationship> reverseOptional = relationshipRepository.findUserRelationshipBetween(passiveUserId, activeUserId);
        boolean empty = optional.isEmpty();
        UserRelationship userRelationship = new UserRelationship();
        if (!empty) {
            userRelationship = optional.get();
        }

        switch (relationshipType) {
            case FOLLOWER -> {
                if (empty) {
                    userRelationship = new UserRelationship();
                    userRelationship.setActiveUserId(activeUserId);
                    userRelationship.setPassiveUserId(passiveUserId);
                    userRelationship.setRelationshipType(relationshipType);
                    userRelationship.setEstablishedAt(LocalDateTime.now());
                    relationshipRepository.save(userRelationship);
                    break;
                }
            }
            case BLACKLIST -> {
                userRelationship.setRelationshipType(relationshipType);
                userRelationship.setEstablishedAt(LocalDateTime.now());
                if (empty) {
                    userRelationship.setActiveUserId(activeUserId);
                    userRelationship.setPassiveUserId(passiveUserId);
                    relationshipRepository.save(userRelationship);
                } else {
                    //reverse direction check and delete
                }
            }
            case CLOSE_FRIEND -> {
                if (!empty && userRelationship.getRelationshipType().equals(RelationshipType.BLACKLIST)) {

                }
                break;
            }
        }
    }

    public void syncUserEntity(String message) {
        try {
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
            JsonNode rootNode = objectMapper.readTree(message);
            JsonNode beforeJsonNode = rootNode.get("payload").get("before");
            JsonNode afterJsonNode = rootNode.get("payload").get("after");
            String operation = rootNode.get("payload").get("op").asText();
            System.out.println(operation);
            System.out.println(message);
            //delete
            switch (operation) {
                case "u": {
                    UserEntityCDC userEntityCDC = objectMapper.treeToValue(afterJsonNode,
                            UserEntityCDC.class);
                    System.out.println(userEntityCDC);
                    UserEntity userEntity = userEntityCDC.toEntity();
                    userRepository.save(userEntity);
                    break;
                }
                case "d": {
                    UserEntityCDC userEntityCDC = objectMapper.treeToValue(beforeJsonNode,
                            UserEntityCDC.class);
                    userRepository.delete(userEntityCDC.toEntity());
                    break;
                }
                case "r": {
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
