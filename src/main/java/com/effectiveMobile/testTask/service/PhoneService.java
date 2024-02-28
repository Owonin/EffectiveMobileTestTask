package com.effectiveMobile.testTask.service;

public interface PhoneService {

    /**
     * Удаление телефона пользователя
     *
     * @param login             Логин пользователя
     * @param deletePhoneNumber Номер телефона для удаления пользователя
     */
    void deletePhone(String login, String deletePhoneNumber);

    void createPhone(String login, String addPhoneNumber);

    void updatePhone(String login, String oldPhoneNumber, String updatePhoneNumber);
}
