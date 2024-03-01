package com.effectiveMobile.testTask.service;

import com.effectiveMobile.testTask.dto.EmailDto;

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
     *
     * @param oldEmailDto Dto старого электронного почтового адреса
     * @param newEmailDto Dto нового электронного почтового адреса
     */
    void updateEmail(EmailDto oldEmailDto, EmailDto newEmailDto);
}
