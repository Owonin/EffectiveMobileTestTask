package com.effectiveMobile.testTask.repository;

import com.effectiveMobile.testTask.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
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

    /**
     * Найти пользователей по заданному фильтру
     *
     * @param fullName Начало имя пользователя
     * @param phoneNumber Номер телефона пользователя
     * @param email Почта пользователя
     * @param birthDate День рождения пользователя
     * @return Список найденных пользователей
     */
    @Query("SELECT u FROM UserEntity u " +
            "LEFT JOIN u.emails e " +
            "LEFT JOIN u.phones p " +
            "WHERE " +
            "(:username IS NULL OR u.username LIKE :username%) AND " +
            "(:email IS NULL OR e.email = :email) AND " +
            "(:phone IS NULL OR p.phone = :phone) AND " +
            "(:birthday IS NULL OR u.birthday > :birthday)")
    Page<UserEntity> findUsersWithFilter(@Param("username") String fullName,
                                         @Param("phone") String phoneNumber,
                                         @Param("email") String email,
                                         @Param("birthday") LocalDate birthDate,
                                         Pageable pageable);

    List<UserEntity> findUsersByIsLimitAchievedFalse();
}
