package org.example.securingapis.service;

import jakarta.persistence.Cacheable;
import org.example.securingapis.dto.CredentialsDto;
import org.example.securingapis.dto.RegistrationDto;
import org.example.securingapis.config.CacheConfig;
import org.example.securingapis.models.User;
import org.example.securingapis.repository.UserRepo2;
import org.example.securingapis.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
//    private final UserRepo2 userRepo2;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(CredentialsDto credentials) {
        User user = userRepository.findByUsername(credentials.getUsername());
        if (user == null || !user.getPassword().equals(credentials.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return user;
    }

//    @Cacheable(value = "users",key = "username")
    public User findByUsername(String username) {
//        User user = userRepository.findByUsername(username);

        return userRepository.findByUsername(username);
    }

    public User register(RegistrationDto registration) {
        if (userRepository.findByUsername(registration.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User(registration.getID(), registration.getUsername(), registration.getPassword(), registration.getRoles());
        userRepository.addUser(user);
        return user;
    }
}