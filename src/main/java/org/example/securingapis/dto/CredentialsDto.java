package org.example.securingapis.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CredentialsDto {

    private final String id;

    @NotNull(message = "Username is req")
    @Size(min = 2, max = 30, message = "Username must be between 2 and 30 characters.")
    private final String username;

    @NotNull(message = "Email is req")
    @Email(message = "Invalid email format")
    private final String password;

    public CredentialsDto(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public String getID(){
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}