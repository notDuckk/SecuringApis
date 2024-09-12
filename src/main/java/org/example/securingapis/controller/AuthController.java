package org.example.securingapis.controller;

import jakarta.validation.Valid;
import org.example.securingapis.config.UserAuthProvider;
import org.example.securingapis.dto.CredentialsDto;
import org.example.securingapis.dto.RegistrationDto;
import org.example.securingapis.models.User;
import org.example.securingapis.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    public AuthController(UserService userService, UserAuthProvider userAuthProvider) {
        this.userService = userService;
        this.userAuthProvider = userAuthProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody CredentialsDto credentials) {
        System.out.println("usernameL:" + credentials.getUsername());
        System.out.println("passwordL:" + credentials.getPassword());

        User user = userService.login(credentials);

        user.setToken(userAuthProvider.createToken(user.getUsername()));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegistrationDto registration) {
        User user = userService.register(registration);

        user.setToken(userAuthProvider.createToken(user.getUsername()));
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/details")
    public String getDetails() {
        return "All users can see this";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/details")
    public String getPrivateDetails() {
        return "Only admins can see this";
    }
}
