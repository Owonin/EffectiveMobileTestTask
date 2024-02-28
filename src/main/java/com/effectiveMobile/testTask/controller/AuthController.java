package com.effectiveMobile.testTask.controller;

import domain.dto.EmailDto;
import domain.dto.PhoneDto;
import domain.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

/**
 * Служебное API для регистрации пользователей
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @PostMapping
    ResponseEntity<?> login(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Dto is accepted");
    }

    @GetMapping
    ResponseEntity<UserDto> giveMeDto() { //todo delete
        PhoneDto phoneDto = PhoneDto.builder().phone("+7 800 800 80 80").build();
        EmailDto emailDtoDto = EmailDto.builder().email("Alex@mail.com").build();

        UserDto userDto = UserDto.builder()
                .money(new BigDecimal(100))
                .birthday(LocalDate.now())
                .login("login")
                .password("psw")
                .id(1L)
                .emails(Set.of(emailDtoDto))
                .phones(Set.of(phoneDto))
                .build();

        return ResponseEntity.ok(userDto);
    }


}
