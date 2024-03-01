package com.effectiveMobile.testTask.controller;

import com.effectiveMobile.testTask.request.UserCreationRequest;
import com.effectiveMobile.testTask.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Служебное API для регистрации пользователей
 */
@RestController
@RequestMapping("api/public/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final UserService userService;

    @Operation(summary = "Endpoint регистрации пользователя")
    @PostMapping
    ResponseEntity<?> regUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        userService.userSingUp(userCreationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь создан");
    }
}
