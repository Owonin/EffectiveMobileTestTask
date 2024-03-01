package com.effectiveMobile.testTask.service;

import com.effectiveMobile.testTask.entity.UserEntity;
import com.effectiveMobile.testTask.response.JwtResponse;


/**
 * Сервис работы с jwt
 */
public interface JwtService {

    /**
     * Генерация токенов
     *
     * @param userEntity Пользователь
     * @return Токены для авторизации
     */
    JwtResponse createToken(UserEntity userEntity);

    /**
     * Обновление access токена
     *
     * @param refreshToken refresh токен
     * @return Обновленные токены
     */
    JwtResponse refresh(String refreshToken);
}
