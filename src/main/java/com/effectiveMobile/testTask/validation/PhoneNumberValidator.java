package com.effectiveMobile.testTask.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber,String> {

    /**
     * Regex выражение для номера телефона формата +7 ХХХ ХХХ ХХ ХХ
     */
    public static final String PHONE_REGEX = "^\\+7\\s[0-9]?[0-9]{2}\\s[0-9]{3}\\s[0-9]{2}\\s[0-9]{2}$";

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * Валидация номера телефона
     *
     * @param phoneNumber Номер телефона
     * @param constraintValidatorContext
     * @return Результат валидации
     */
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        return phoneNumber != null && phoneNumber.matches(PHONE_REGEX);
    }
}
