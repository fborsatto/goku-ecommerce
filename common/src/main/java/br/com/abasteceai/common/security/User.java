package br.com.abasteceai.common.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class User {
    @JsonIgnore
    @Id
    private String id;
    @JsonIgnore
    private String password;
    private String login;
    private List<String> roles = new ArrayList<>();

    public User() {
        this.id = UUID.randomUUID().toString();
    }
}