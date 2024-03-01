package com.effectiveMobile.testTask.service;

import com.effectiveMobile.testTask.dto.EmailDto;
import com.effectiveMobile.testTask.dto.EmailUpdateDto;

/**
 * Сервис работы с электронными почтами
 */
public interface EmailService {
    /**
     * Удаление электронной почты
     *
     * @param emailDto Dto электронного почтового адреса
     */
    void deleteEmail(EmailDto emailDto);

    /**
     * Создание электронной почты
     *
     * @param emailDto Dto электронного почтового адреса
     */
    void createEmail(EmailDto emailDto);


    /**
     * Обновление электронноый почты
     */
    void updateEmail(EmailUpdateDto emailUpdateDto);
}
