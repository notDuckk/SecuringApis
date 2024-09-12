package org.example.securingapis.dto;


public class RegistrationDto extends CredentialsDto {
    private final String[] roles;

    public RegistrationDto(String id, String username, String password, String[] roles) {
        super(id, username, password);
        this.roles = roles;
    }

    public String[] getRoles() {
        return roles;
    }
}