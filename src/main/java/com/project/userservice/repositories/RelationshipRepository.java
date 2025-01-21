package com.project.userservice.repositories;

import com.project.userservice.entities.UserRelationship;
import org.aspectj.asm.internal.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelationshipRepository extends JpaRepository<UserRelationship, Long> {
    @Query("SELECT ur FROM UserRelationship ur" +
            " WHERE ur.activeUserId = :activeUserId AND ur.passiveUserId = :passiveUserId")
    Optional<UserRelationship> findUserRelationshipBetween(String activeUserId, String passiveUserId);
}
