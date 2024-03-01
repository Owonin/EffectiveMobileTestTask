package com.effectiveMobile.testTask.request;

import com.effectiveMobile.testTask.dto.EmailDto;
import com.effectiveMobile.testTask.dto.PhoneDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequest {
    @NotBlank(message = "Логин пользователя не должно быть пустым")
    @Size(min = 6, max = 20, message = "Имя пользователя должно быть от 6 до 20 знаков")
    private String login;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 6, max = 50, message = "Пароль должен быть от 6 до 20 знаков")
    private String password;

    @NotBlank(message = "ФИО пользователя не должно быть пустым")
    private String username;

    @Past
    @NotNull(message = "Дата рождения пользователя не должно быть пустым")
    private LocalDate birthday;

    @Valid
    private Set<EmailDto> emails;

    @Valid
    private Set<PhoneDto> phones;

    @Positive
    @DecimalMin(value = "1.00", message = "Начальные накапления не должны быть пустыми")
    private BigDecimal startBalance;
}