package com.effectiveMobile.testTask.service;

import com.effectiveMobile.testTask.entity.UserEntity;
import com.effectiveMobile.testTask.request.TransactionRequest;
import com.effectiveMobile.testTask.request.UserCreationRequest;
import com.effectiveMobile.testTask.response.UserListResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Сервис для работы с пользователями
 */

public interface UserService extends UserDetailsService {

    /**
     * Получение пользователей по данному фильтру
     *
     * @param usernameFilter Фильтр по имени пользователя
     * @param phoneFilter    Фильтр по телефонному номеру пользователя
     * @param birthdayFilter Фильтр по дню рождения пользователя
     * @param emailFilter    Фильтр по электронному почтовому адресу пользователя
     * @param page           Страница пагинации пользователей
     * @param size           Количество пользователей на странице
     * @param sort           Параметры сортировки данных
     * @return Список найденных пользователей
     */
    UserListResponse getAllUsers(int page, int size, String[] sort, String usernameFilter, String emailFilter,
                                 String birthdayFilter, String phoneFilter);

    /**
     * Регистрация пользователей
     *
     * @param userCreationRequest Данные пользователя
     */
    void userSingUp(UserCreationRequest userCreationRequest);

    /**
     * Перевод денег на счет другово пользователя
     *
     * @param recipientLogin     Логин получателя
     * @param transactionRequest Данные о сумме транзакции
     */
    void moneyExchange(String recipientLogin, TransactionRequest transactionRequest);

    /**
     * Увеличение баланса всех пользователей
     */
    void increaseAllUsersBalance();

    /**
     * Получение авторизованного пользователя
     *
     * @return Данные авторизованного пользователя
     */
    UserEntity getAuthenticatedUserEntity();
}
