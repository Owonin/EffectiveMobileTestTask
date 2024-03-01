package com.effectiveMobile.testTask.controller;

import com.effectiveMobile.testTask.dto.PhoneDto;
import com.effectiveMobile.testTask.service.PhoneService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/private/phones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class PhoneController {

    private final PhoneService phoneService;

    /**
     * Endpoint создания номера телефона
     *
     * @param phone Dto номера телефона
     * @return ResponseEntity
     */
    @Operation(summary = "Endpoint создания номера телефона")
    @PostMapping
    public ResponseEntity<String> saveNewUserPhone(@RequestBody @Valid PhoneDto phone) {
        phoneService.createPhone(phone);

        return ResponseEntity.status(HttpStatus.CREATED).body("Номер телефона сохранен");
    }

    /**
     * Endpoint изменения номера телефона
     *
     * @param newPhone Dto нового номера телефона
     * @param oldPhone Dto старого номера телефона
     * @return ResponseEntity
     */
    @Operation(summary = "Endpoint изменения номера телефона")
    @PatchMapping
    public ResponseEntity<String> transaction(@RequestBody @Valid PhoneDto newPhone,
                                              @RequestBody @Valid PhoneDto oldPhone) {
        phoneService.updatePhone(oldPhone, newPhone);

        return ResponseEntity.ok("Номер телефона изменен успешно");
    }

    /**
     * Endpoint удаления номера телефона
     *
     * @param phone Dto номера телефона
     * @return ResponseEntity
     */
    @Operation(summary = "Endpoint удаления номера телефона")
    @DeleteMapping
    public ResponseEntity<String> deleteUserPhone(@RequestBody @Valid PhoneDto phone) {
        phoneService.deletePhone(phone);

        return ResponseEntity.status(HttpStatus.CREATED).body("Почта сохранена");
    }
}