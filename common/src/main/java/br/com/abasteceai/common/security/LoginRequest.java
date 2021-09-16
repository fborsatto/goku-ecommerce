package br.com.abasteceai.common.security;

import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String password;
}