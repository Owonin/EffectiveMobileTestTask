package com.effectiveMobile.testTask.controller;

import com.effectiveMobile.testTask.dto.EmailDto;
import com.effectiveMobile.testTask.dto.PhoneDto;
import com.effectiveMobile.testTask.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Служебное API для регистрации пользователей
 */
@RestController
@RequestMapping("public/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @PostMapping
    ResponseEntity<?> login(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }
}
