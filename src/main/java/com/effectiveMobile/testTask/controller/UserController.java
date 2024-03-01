package com.effectiveMobile.testTask.controller;

import com.effectiveMobile.testTask.request.TransactionRequest;
import com.effectiveMobile.testTask.response.UserListResponse;
import com.effectiveMobile.testTask.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер работы с пользователем
 */
@RestController
@RequestMapping("api/private/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserService userService;

    /**
     * Endpoint для получения пользователей по данному фильтру
     *
     * @param usernameFilter Фильтр по имени пользователя
     * @param phoneFilter    Фильтр по телефонному номеру пользователя
     * @param birthdayFilter Фильтр по дню рождения пользователя
     * @param emailFilter    Фильтр по электронному почтовому адресу пользователя
     * @param page           Страница пагинации пользователей
     * @param size           Количество пользователей на странице
     * @param sort           Параметры сортировки данных
     * @return Список найденных пользователей
     */
    @Operation(summary = "Endpoint получения списка пользователей")
    @GetMapping
    public ResponseEntity<UserListResponse> getAllUsers(@Parameter(description = "Параметр фильтации по ФИО")
                                                        @RequestParam(required = false) String usernameFilter,
                                                        @Parameter(description = "Параметр фильтрации по телефону")
                                                        @RequestParam(required = false) String phoneFilter,
                                                        @Parameter(description = "Параметр фильтрации по дате рождения")
                                                        @RequestParam(required = false) String birthdayFilter,
                                                        @Parameter(description = "Параметр фильтрации по почтовому адресу")
                                                        @RequestParam(required = false) String emailFilter,
                                                        @Parameter(description = "Номер страницы")
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @Parameter(description = "Количество элементов страницы")
                                                        @RequestParam(defaultValue = "10") int size,
                                                        @Parameter(description = "Параметр сортировки и ее порядок (убывание или возрастание)")
                                                        @RequestParam(defaultValue = "id,desc") String[] sort) {
        var usersPage = userService.getAllUsers(page, size, sort, usernameFilter, emailFilter, birthdayFilter, phoneFilter);

        return ResponseEntity.ok(usersPage);
    }

    /**
     * Endpoint перевода денег на счет другого пользователя
     *
     * @param recipientLogin     Логин получателя
     * @param transactionRequest Запрос транзакции
     * @return ResponseEntity
     */
    @Operation(summary = "Endpoint перевода денег на счет другого пользователя")
    @PostMapping("/transaction/{recipientLogin}")
    public ResponseEntity<String> transaction(@PathVariable("recipientLogin") String recipientLogin, //todo swagger
                                              @RequestBody @Valid TransactionRequest transactionRequest) {
        userService.moneyExchange(recipientLogin, transactionRequest);
        return ResponseEntity.ok("Перевод выполнен");
    }
}
