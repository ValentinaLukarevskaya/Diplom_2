package com.valya.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String email;
    private String password;
    private String name;
    public String accessToken;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}