package com.effectiveMobile.testTask.service;

import com.effectiveMobile.testTask.exception.NotFoundException;
import domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    public static final String USERNAME_NOT_FOUND_EXCEPTION_MESSAGE = "Пользователь с логином %s не был найден";
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByLogin(username).orElseThrow(() ->
                new NotFoundException(USERNAME_NOT_FOUND_EXCEPTION_MESSAGE.formatted(username)));
    }
}
