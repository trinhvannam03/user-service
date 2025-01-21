package com.project.userservice.entities;

import com.project.userservice.enums.RelationshipType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long relationshipId;

    private LocalDateTime establishedAt;

    @Column(name = "active_user_id", nullable = false)
    private String activeUserId;

    @Column(name = "passive_user_id", nullable = false)
    private String passiveUserId;

    @Enumerated(EnumType.STRING)
    private RelationshipType relationshipType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "active_user_id", referencedColumnName = "user_entity_id", insertable = false, updatable = false)
    private UserEntity activeUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passive_user_id", referencedColumnName = "user_entity_id", insertable = false, updatable = false)
    private UserEntity passiveUser;

    @PrePersist
    public void prePersist() {
        if (establishedAt == null) {
            establishedAt = LocalDateTime.now();
        }
    }
}
