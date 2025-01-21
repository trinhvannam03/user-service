package com.project.userservice.repositories;

import com.project.userservice.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findUserEntityByUserEntityId(String userEntityId);

    @Query("SELECT u from UserEntity u " +
            "JOIN FETCH u.activeRelationships ar " +
            "WHERE ar.activeUserId = :userId AND ar.relationshipType = 'FOLLOWER'")
    List<UserEntity> findFollowers(@Param("userId") String userId);
}
