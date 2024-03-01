package com.effectiveMobile.testTask.service;

import com.effectiveMobile.testTask.request.AuthenticationRequest;
import com.effectiveMobile.testTask.response.JwtResponse;


/**
 * Сервис работы с jwt
 */
public interface JwtService {

    /**
     * Генерация токенов
     *
     * @param request Запрос авторизации
     * @return Токены для авторизации
     */
    JwtResponse createToken(AuthenticationRequest request);

    /**
     * Обновление access токена
     *
     * @param refreshToken refresh токен
     * @return Обновленные токены
     */
    JwtResponse refresh(String refreshToken);
}
