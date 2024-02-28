package com.effectiveMobile.testTask.service;

import com.effectiveMobile.testTask.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.math.BigDecimal;

/**
 * Сервис для работы с пользователями
 */

public interface UserService extends UserDetailsService {

    UserDto getAllUsers();

    UserDto getUserById(Long id);

    void moneyExchange(String user1, String user2, BigDecimal amount);

    void increaseMoney();
}
