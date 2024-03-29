package com.effectiveMobile.testTask.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailUpdateDto {
    @Email
    @NotEmpty(message = "Email не должен быть пустым")
    private String oldEmail;

    @Email
    @NotEmpty(message = "Email не должен быть пустым")
    private String newEmail;
}
