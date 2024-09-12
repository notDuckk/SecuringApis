package org.example.securingapis.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.redis.core.RedisHash;


@Entity
@RedisHash("users")
public class User {

    @Id
    private String id;

    private String username;
    private String password;
    private String[] roles;
    private String token = null;

    public User(String id, String username, String password, String[] roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String[] getRoles() {
        return roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }
}