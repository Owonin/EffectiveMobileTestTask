package com.effectiveMobile.testTask.repository;

import com.effectiveMobile.testTask.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Получение пользователя по логину
     *
     * @param login Логин пользователя
     * @return Объект {@link Optional} параметризированный пользователем
     */
    Optional<UserEntity> findUserByLogin(String login);
}
