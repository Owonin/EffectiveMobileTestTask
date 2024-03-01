package com.effectiveMobile.testTask.controller;

import com.effectiveMobile.testTask.dto.EmailDto;
import com.effectiveMobile.testTask.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/private/emails")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmailController {

    private final EmailService emailService;

    /**
     * Endpoint создания новой записи почты
     *
     * @param emailDto Dto адреса электронной почты
     * @return ResponseEntity
     */
    @Operation(summary = "Endpoint создания новой записи почты")
    @PostMapping
    public ResponseEntity<String> saveNewUserEmail(@RequestBody @Valid EmailDto emailDto) {
        emailService.createEmail(emailDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Почта сохранена");
    }

    /**
     * Endpoint создания новой записи почты
     *
     * @param newEmail Dto старого адреса электронной почты
     * @param oldEmail Dto адреса нового электронной почты
     * @return ResponseEntity
     */
    @Operation(summary = "Endpoint изменения записи почты")
    @PatchMapping
    public ResponseEntity<String> transaction(@RequestBody @Valid EmailDto newEmail,
                                              @RequestBody @Valid EmailDto oldEmail) {
        emailService.updateEmail(oldEmail, newEmail);

        return ResponseEntity.ok("Почтовый адрес изменен успешно");
    }

    /**
     * Endpoint удаления записи почты
     *
     * @param emailDto Dto адреса электронной почты
     * @return ResponseEntity
     */
    @Operation(summary = "Endpoint удаления записи почты")
    @DeleteMapping
    public ResponseEntity<String> deleteUserEmail(@RequestBody @Valid EmailDto emailDto) {
        emailService.deleteEmail(emailDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Почта сохранена");
    }
}