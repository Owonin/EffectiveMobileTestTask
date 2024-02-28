package com.effectiveMobile.testTask.dto;

import com.effectiveMobile.testTask.validation.PhoneNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

/**
 * DTO сущности телефонных номеров
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDto {

    @Positive
    private Long id;

    @NotBlank(message = "Номер телефона не должен быть пустым")
    @PhoneNumber(message = "Не верный формат номера телефона, введите номер в формате +7 ХХХ ХХХ ХХ ХХ")
    private String phone;

    @NotBlank
    private UserDto user;
}
