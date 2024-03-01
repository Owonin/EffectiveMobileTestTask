package com.effectiveMobile.testTask.repository;

import com.effectiveMobile.testTask.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, Long> {
    boolean existsByEmail(String email);
    Optional<EmailEntity> findByEmail(String email);
}
