package com.effectiveMobile.testTask.repository;

import com.effectiveMobile.testTask.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {
    boolean existsByPhone(String phone);

    Optional<PhoneEntity> findByPhone(String phone);
}
