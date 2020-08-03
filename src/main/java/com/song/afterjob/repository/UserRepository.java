package com.song.afterjob.repository;

import com.song.afterjob.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String userId);
    long countByEmail(String userId);
}
