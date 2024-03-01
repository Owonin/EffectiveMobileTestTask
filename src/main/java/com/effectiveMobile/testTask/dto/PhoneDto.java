package com.effectiveMobile.testTask.dto;

import com.effectiveMobile.testTask.validation.PhoneNumber;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO сущности телефонных номеров
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDto {
    @NotBlank(message = "Номер телефона не должен быть пустым")
    @PhoneNumber(message = "Не верный формат номера телефона, введите номер в формате +7 ХХХ ХХХ ХХ ХХ")
    private String phone;
}
