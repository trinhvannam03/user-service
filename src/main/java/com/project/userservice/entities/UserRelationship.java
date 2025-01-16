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
    //
    @ManyToOne
    @JoinColumn(name = "active_user", insertable = false, updatable = false)
    private UserEntity activeUser;

    @ManyToOne
    @JoinColumn(name = "passive_user", insertable = false, updatable = false)
    private UserEntity passiveUser;

    @Column(name = "active_user_id")
    private String activeUserId;

    @Column(name = "passive_user_id")
    private String passiveUserId;

    @Enumerated(EnumType.STRING)
    private RelationshipType relationshipType;
}
