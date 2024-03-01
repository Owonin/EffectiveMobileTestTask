package com.effectiveMobile.testTask.request;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String login;
    private String password;
}
