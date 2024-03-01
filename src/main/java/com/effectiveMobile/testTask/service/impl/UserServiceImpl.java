package com.effectiveMobile.testTask.service.impl;

import com.effectiveMobile.testTask.aop.annotation.Loggable;
import com.effectiveMobile.testTask.entity.Role;
import com.effectiveMobile.testTask.entity.UserEntity;
import com.effectiveMobile.testTask.exception.BadRequestException;
import com.effectiveMobile.testTask.exception.NotFoundException;
import com.effectiveMobile.testTask.mapper.UserMapper;
import com.effectiveMobile.testTask.repository.UserRepository;
import com.effectiveMobile.testTask.request.TransactionRequest;
import com.effectiveMobile.testTask.request.UserCreationRequest;
import com.effectiveMobile.testTask.response.UserListResponse;
import com.effectiveMobile.testTask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Loggable
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public static final String USERNAME_NOT_FOUND_EXCEPTION_MESSAGE_WITH_LOGIN = "Пользователь с логином %s не был найден";
    public static final String USERNAME_NOT_FOUND_EXCEPTION_MESSAGE = "Пользователь не был найден";
    public static final String NOT_ENOUGH_BALANCE_ERROR_MESSAGE = "Недостаточно средст на балансе для перевода";

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return findUserByLogin(login);
    }

    @Override
    public UserListResponse getAllUsers(int page, int size, String[] sort,
                                        String usernameFilter,
                                        String emailFilter,
                                        String birthdayFilter,
                                        String phoneFilter) {

        var orders = getSortOrders(sort);

        LocalDate birthdayLocalDateFilter = null;
        if (birthdayFilter != null) {
            birthdayLocalDateFilter = LocalDate.parse(birthdayFilter);
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));

        var usersPage = userRepository.findUsersWithFilter(usernameFilter,
                        phoneFilter,
                        emailFilter,
                        birthdayLocalDateFilter,
                        pageable)
                .map(userMapper::userEntityToUserDto);

        return UserListResponse.builder()
                .users(usersPage.getContent())
                .currentPage(page)
                .totalPages(usersPage.getTotalPages())
                .totalItems(usersPage.getTotalElements())
                .build();
    }

    @Override
    public void userSingUp(UserCreationRequest userCreationRequest) {
        var userEntity = userMapper.userCreationRequestToUserEntity(userCreationRequest);
        userEntity.setCreationDateTime(LocalDateTime.now());
        userEntity.setRole(Role.USER_ROLE);
        userEntity.setCurrentBalance(userEntity.getStartBalance());

        userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public synchronized void moneyExchange(String recipientLogin, TransactionRequest transactionRequest) {
        var amount = transactionRequest.getAmount();
        var sender = getAuthenticatedUserEntity();
        var recipient = findUserByLogin(recipientLogin);
        var senderBudget = sender.getCurrentBalance();
        var recipientBudget = recipient.getCurrentBalance();

        if (senderBudget.compareTo(amount) < 0) {
            sender.setCurrentBalance(senderBudget.add(amount.negate()));
            recipient.setCurrentBalance(recipientBudget.add(amount));

            var senderLimit = sender.getStartBalance().add(sender.getStartBalance().multiply(new BigDecimal("2.07")));
            var recipientLimit = recipient.getStartBalance().add(recipient.getStartBalance().multiply(new BigDecimal("2.07")));

            if (senderBudget.compareTo(senderLimit) >= 0) {
                sender.setLimitAchieved(true);
            }
            if (recipientBudget.compareTo(recipientLimit) >= 0) {
                sender.setLimitAchieved(true);
            }
        } else {
            throw new BadRequestException(NOT_ENOUGH_BALANCE_ERROR_MESSAGE);
        }

        userRepository.save(sender);
        userRepository.save(recipient);
    }

    @Override
    @Transactional
    public synchronized void increaseAllUsersBalance() {
        var users = userRepository.findUsersByIsLimitAchievedFalse();
        users.forEach(userEntity -> {
            var startBalance = userEntity.getStartBalance();
            var balance = userEntity.getCurrentBalance();
            var tempBalance = balance.add(balance.multiply(new BigDecimal("0.05")));
            var limit = startBalance.add(startBalance.multiply(new BigDecimal("2.07")));
            if (tempBalance.compareTo(limit) < 0) {
                userEntity.setCurrentBalance(tempBalance);
                userRepository.save(userEntity);
            } else {
                userEntity.setLimitAchieved(true);
                userRepository.save(userEntity);
            }
        });
    }

    @Override
    public UserEntity getAuthenticatedUserEntity() {
        var login = getCurrentAuthenticatedUserLogin();

        return userRepository.findUserByLogin(login).orElseThrow(() ->
                new NotFoundException(USERNAME_NOT_FOUND_EXCEPTION_MESSAGE_WITH_LOGIN.formatted(login)));
    }

    /**
     * Получение порядка сортировка
     *
     * @param sort Строка сортировки
     * @return Набор объектов {@link Sort.Order}
     */
    private List<Sort.Order> getSortOrders(String[] sort) {
        List<Sort.Order> orders = new ArrayList<>();

        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                var sortArguments = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(sortArguments[1]),
                        sortArguments[0]));
            }
        } else {
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }

        return orders;
    }

    /**
     * Получение порядка сортировки (возрастание или убывание)
     *
     * @param sortDirection
     * @return Объект {@link Sort.Direction}
     */
    private Sort.Direction getSortDirection(String sortDirection) {
        if (sortDirection.toLowerCase().trim().contains("asc")) {
            return Sort.Direction.ASC;
        }

        if (sortDirection.toLowerCase().trim().contains("desc")) {
            return Sort.Direction.DESC;
        }

        return null;
    }


    /**
     * Получение сущности пользователя по логину
     *
     * @param login Логин пользователя
     * @return Сущность пользователя
     */
    private UserEntity findUserByLogin(String login) {
        return userRepository.findUserByLogin(login).orElseThrow(() ->
                new NotFoundException(USERNAME_NOT_FOUND_EXCEPTION_MESSAGE_WITH_LOGIN.formatted(login)));
    }


    /**
     * Получение логина авторизованного пользователя
     *
     * @return Логин авторизованного пользователя
     */
    private String getCurrentAuthenticatedUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
