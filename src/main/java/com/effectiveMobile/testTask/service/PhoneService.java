package com.effectiveMobile.testTask.service;


import com.effectiveMobile.testTask.dto.PhoneDto;

/**
 * Сервис работы с электронными почтами
 */
public interface PhoneService {
    /**
     * Удаление электронной почты
     *
     * @param phoneDto Dto электронного почтового адреса
     */
    void deletePhone(PhoneDto phoneDto);

    /**
     * Создание электронной почты
     *
     * @param phoneDto Dto электронного почтового адреса
     */
    void createPhone(PhoneDto phoneDto);


    /**
     * Обновление электронноый почты
     *
     * @param oldPhoneDto Dto старого электронного почтового адреса
     * @param newPhoneDto Dto нового электронного почтового адреса
     */
    void updatePhone(PhoneDto oldPhoneDto, PhoneDto newPhoneDto);
}