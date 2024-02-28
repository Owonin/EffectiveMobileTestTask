package com.effectiveMobile.testTask.service;


import com.effectiveMobile.testTask.dto.JwtDto;
import com.effectiveMobile.testTask.dto.UserDto;
import com.effectiveMobile.testTask.entity.UserEntity;
import com.effectiveMobile.testTask.repository.UserRepository;
import com.effectiveMobile.testTask.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;


    public void singUp(UserDto userDto) {

        var userEntity = new UserEntity();

        //userRepository.save(user);
    }

    public JwtDto createToken(UserDto userDto) {
      return null;
    }

    @Override
    public JwtDto refresh(String refreshToken) {
        return null;
    }

}