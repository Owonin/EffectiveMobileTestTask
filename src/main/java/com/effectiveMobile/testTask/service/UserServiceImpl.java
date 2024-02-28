package com.effectiveMobile.testTask.service;

import com.effectiveMobile.testTask.dto.UserDto;
import com.effectiveMobile.testTask.entity.UserEntity;
import com.effectiveMobile.testTask.exception.NotFoundException;
import com.effectiveMobile.testTask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public static final String USERNAME_NOT_FOUND_EXCEPTION_MESSAGE_WITH_LOGIN = "Пользователь с логином %s не был найден";
    public static final String USERNAME_NOT_FOUND_EXCEPTION_MESSAGE = "Пользователь не был найден";

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return findUserByLogin(login);
    }

    @Override
    public UserDto getAllUsers() { //todo userDtoList
        return null;
    }

    private Sort.Direction getSortDirection(String sortDirection) {
        if (sortDirection.toLowerCase().trim().contains("asc")) {
            return Sort.Direction.ASC;
        }

        if (sortDirection.toLowerCase().trim().contains("desc")) {
            return Sort.Direction.DESC;
        }

        return null;
    }

    @Override
    public UserDto getUserById(Long id) {
        var userEntity = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(USERNAME_NOT_FOUND_EXCEPTION_MESSAGE));
        return new UserDto(); //todo mapping
    }

    @Override
    @Transactional
    public synchronized void moneyExchange(String senderLogin, String recipientLogin, BigDecimal amount) {
        var sender = findUserByLogin(senderLogin);
        var recipient = findUserByLogin(recipientLogin);

        var senderBudget = sender.getCurrentBalance();
        var recipientBudget = recipient.getCurrentBalance();

        if (senderBudget.compareTo(amount) <= 0) {
            sender.setCurrentBalance(senderBudget.add(amount.negate()));
            recipient.setCurrentBalance(recipientBudget.add(amount));
        } else {
            throw new RuntimeException("mesage"); // todo
        }

        userRepository.save(sender);
        userRepository.save(recipient);
    }

    @Override
    @Transactional(noRollbackFor = RuntimeException.class) //todo add exception
    public synchronized void increaseMoney() { //todo optimization
        var users = userRepository.findAll();
        users.forEach(userEntity -> {
            var startBalance = userEntity.getStartBalance();
            var balance = userEntity.getCurrentBalance();
            var tempBalance = balance.add(balance.multiply(new BigDecimal("0.05")));
            var limit = startBalance.add(startBalance.multiply(new BigDecimal("2.07")));
            if (tempBalance.compareTo(limit) < 0) {
                userEntity.setCurrentBalance(tempBalance);
                userRepository.save(userEntity);
            } else {
                throw new RuntimeException("Message"); //todo add
            }
        });
    }


    private UserEntity findUserByLogin(String login) {
        return userRepository.findUserByLogin(login).orElseThrow(() ->
                new NotFoundException(USERNAME_NOT_FOUND_EXCEPTION_MESSAGE_WITH_LOGIN.formatted(login)));
    }
}
