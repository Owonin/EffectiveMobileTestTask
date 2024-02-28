package com.effectiveMobile.testTask.service;

import com.effectiveMobile.testTask.dto.JwtDto;
import com.effectiveMobile.testTask.dto.UserDto;


public interface JwtService {

    void singUp(UserDto user);

    JwtDto createToken(UserDto userDto);

    JwtDto refresh(String refreshToken);
}
