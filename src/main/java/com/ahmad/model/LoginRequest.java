package com.ahmad.model;
import java.io.Serializable;
import lombok.Data;

@Data
public class LoginRequest implements Serializable {
    private String username;
    private String password;
}