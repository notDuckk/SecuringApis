package org.example.securingapis.repository;

//import jakarta.persistence.Cacheable;
import org.example.securingapis.config.CacheConfig;
import org.example.securingapis.models.User;
import org.example.securingapis.models.Roles;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    UserRepo2 userRepo2;

    private User[] users = {
            new User("1","admin", "admin", new String[] {"USER", "ADMIN"}),
            new User("2","user", "user", new String[] {"USER"})
    };

    public User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Cacheable(value = "users",key = "#id")
    public User addUser(User user) {
        User[] newUsers = new User[users.length + 1];
        System.arraycopy(users, 0, newUsers, 0, users.length);
        newUsers[users.length] = user;
        users = newUsers;
        userRepo2.save(user);
        return user;
    }
}