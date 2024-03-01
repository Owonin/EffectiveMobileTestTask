package com.effectiveMobile.testTask.service;


import com.effectiveMobile.testTask.dto.PhoneDto;
import com.effectiveMobile.testTask.dto.PhoneUpdateDto;

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
     */
    void updatePhone(PhoneUpdateDto phoneUpdateDto);
}