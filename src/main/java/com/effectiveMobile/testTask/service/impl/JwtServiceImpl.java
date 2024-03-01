package com.effectiveMobile.testTask.service.impl;


import com.effectiveMobile.testTask.aop.annotation.Loggable;
import com.effectiveMobile.testTask.entity.UserEntity;
import com.effectiveMobile.testTask.repository.UserRepository;
import com.effectiveMobile.testTask.response.JwtResponse;
import com.effectiveMobile.testTask.service.JwtService;
import com.effectiveMobile.testTask.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Loggable
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;


    public JwtResponse createToken(UserEntity userDto) {
        return null;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return null;
    }

}