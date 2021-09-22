package br.com.abasteceai.common.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    @JsonIgnore
    private String password;

    @Id
    private String login;
    private List<String> roles = new ArrayList<>();
}