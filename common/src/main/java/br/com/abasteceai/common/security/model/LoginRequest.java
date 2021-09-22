package br.com.abasteceai.common.security.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String password;
}