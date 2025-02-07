package com.qaracter.digitalwallet.service;

import com.qaracter.digitalwallet.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains the business logic for user management in the system.
 */
@Service
public class UserService {

    private final Map<Long, User> users = new HashMap<>();
    private Long userIdCounter = 1L; // For generating unique user IDs

    public User createUser(String name) {
        User user = new User(userIdCounter++, name); // Generates a new user with a unique ID
        users.put(user.getId(), user);
        return user;
    }

    public User getUser(Long userId) {
        return users.get(userId);
    }

}
