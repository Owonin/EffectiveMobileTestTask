package com.effectiveMobile.testTask.service.impl;

import com.effectiveMobile.testTask.aop.annotation.Loggable;
import com.effectiveMobile.testTask.dto.PhoneDto;
import com.effectiveMobile.testTask.entity.PhoneEntity;
import com.effectiveMobile.testTask.exception.AuthException;
import com.effectiveMobile.testTask.exception.BadRequestException;
import com.effectiveMobile.testTask.exception.NotFoundException;
import com.effectiveMobile.testTask.mapper.PhoneMapper;
import com.effectiveMobile.testTask.repository.PhoneRepository;
import com.effectiveMobile.testTask.repository.UserRepository;
import com.effectiveMobile.testTask.service.PhoneService;
import com.effectiveMobile.testTask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Loggable
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {
    public static final String PHONE_IS_NOT_FOUND_MESSAGE = "Телефон с номером %s не найден";
    public static final String NOT_ALLOWED_TO_UPDATE_PHONE_ERROR_MESSAGE = "Нельзя редактировать чужой номер телефона";
    public static final String PHONE_IS_NOT_FOUND_ERROR_MESSAGE = "Данный номер %s не найден в системе";
    public static final String PHONE_IS_ALREADY_REGISTRATED_ERROR_MESSAGE = "Данный номер %s уже зарегестрирован в системе";
    public static final String UNABLE_TO_DELETE_ALL_PHONES_ERROR_MESSAGE = "Нельзя удалить все номера телефонов";

    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final UserService userService;
    private final PhoneMapper phoneMapper;


    @Override
    public void deletePhone(PhoneDto deletePhoneDto) {
        var userEntity = userService.getAuthenticatedUserEntity();
        var phones = userEntity.getPhones();
        if (phones.size() > 1) {
            var phoneEntity = findPhoneByNumber(deletePhoneDto.getPhone(), phones);
            phoneRepository.delete(phoneEntity);
        }

        throw new BadRequestException(UNABLE_TO_DELETE_ALL_PHONES_ERROR_MESSAGE);
    }

    @Override
    public void createPhone(PhoneDto newPhoneDto) {
        var phoneEntity = phoneMapper.phoneDtoToPhoneEntity(newPhoneDto);

        phoneRepository.save(phoneEntity);
    }

    @Override
    @Transactional
    public void updatePhone(PhoneDto oldPhoneDto, PhoneDto newPhoneDto) {
        if (phoneRepository.existsByPhone(newPhoneDto.getPhone())) {
            throw new BadRequestException(PHONE_IS_ALREADY_REGISTRATED_ERROR_MESSAGE
                    .formatted(newPhoneDto.getPhone()));
        }

        var phoneEntity = phoneRepository.findByPhone(oldPhoneDto.getPhone())
                .orElseThrow(() -> new NotFoundException(PHONE_IS_NOT_FOUND_ERROR_MESSAGE
                        .formatted(oldPhoneDto.getPhone())));

        if (!phoneEntity.getUser().equals(userService.getAuthenticatedUserEntity())) {
            throw new AuthException(NOT_ALLOWED_TO_UPDATE_PHONE_ERROR_MESSAGE);
        }
        phoneEntity.setPhone(newPhoneDto.getPhone());

        phoneRepository.save(phoneEntity);
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
