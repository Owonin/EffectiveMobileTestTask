package com.effectiveMobile.testTask.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO сущности электронных почтовых адресов
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {
    @Email
    @NotBlank(message = "Email не должен быть пустым")
    private String email;
}
