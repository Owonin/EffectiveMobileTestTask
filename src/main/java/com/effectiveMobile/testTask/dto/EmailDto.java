package com.effectiveMobile.testTask.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

/**
 * DTO сущности электронных почтовых адресов
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {
    @Positive
    private Long id;

    @Email
    @NotBlank(message = "Email не должен быть пустым")
    private String email;

    @NotBlank
    private UserDto user;
}
