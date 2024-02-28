package com.effectiveMobile.testTask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Глобальный обработчик ошибок
 */
@ControllerAdvice
public class CustomResponseExceptionHandler {

    /**
     * Обработчик ошибок валидации
     *
     * @param ex Ошибка валидации
     * @return Сообщение об ошибках валидации
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Обработчик ошибок поиска по БД
     *
     * @param ex Ошибка поиска
     * @return Сообщение об ошибке
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}