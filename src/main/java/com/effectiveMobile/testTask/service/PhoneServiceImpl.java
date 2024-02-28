package com.effectiveMobile.testTask.service;

import com.effectiveMobile.testTask.entity.PhoneEntity;
import com.effectiveMobile.testTask.entity.UserEntity;
import com.effectiveMobile.testTask.exception.NotFoundException;
import com.effectiveMobile.testTask.repository.PhoneRepository;
import com.effectiveMobile.testTask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {

    public static final String USERNAME_NOT_FOUND_EXCEPTION_MESSAGE_WITH_LOGIN = "Пользователь с логином %s не был найден";
    public static final String PHONE_IS_NOT_FOUND_MESSAGE = "Телефон с номером %s не найден";

    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;


    @Override
    public void deletePhone(String login, String deletePhoneNumber) {
        var userEntity = getUserEntity(login);
        var phones = userEntity.getPhones();
        if (phones.size() > 1) {
            var phone = findPhoneByNumber(deletePhoneNumber, phones);

            phoneRepository.delete(phone);
        }
    }

    @Override
    public void createPhone(String login, String addPhoneNumber) {
        var userEntity = getUserEntity(login);
        var phoneEntity = new PhoneEntity();
        phoneEntity.setPhone(addPhoneNumber);
        phoneEntity.setUser(userEntity);

        phoneRepository.save(phoneEntity);
    }

    @Override
    public void updatePhone(String login, String oldPhoneNumber, String updatePhoneNumber) {
        var userEntity = getUserEntity(login);
        var phones = userEntity.getPhones();
        var phoneEntity = findPhoneByNumber(oldPhoneNumber, phones);
        phoneEntity.setPhone(updatePhoneNumber);

        phoneRepository.save(phoneEntity);

    }

    private UserEntity getUserEntity(String login) {
        return userRepository.findUserByLogin(login).orElseThrow(() ->
                new NotFoundException(USERNAME_NOT_FOUND_EXCEPTION_MESSAGE_WITH_LOGIN.formatted(login)));
    }

    private static PhoneEntity findPhoneByNumber(String phoneNumber, Set<PhoneEntity> phones) {
        return phones.stream().filter(phoneEntity ->
                        phoneEntity
                                .getPhone()
                                .equals(phoneNumber))
                .findAny()
                .orElseThrow(() -> new NotFoundException(PHONE_IS_NOT_FOUND_MESSAGE.formatted(phoneNumber)));
    }
}
