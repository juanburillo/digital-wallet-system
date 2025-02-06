package com.qaracter.digitalwallet.service;

import com.qaracter.digitalwallet.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<Long, User> users = new HashMap<>();
    private Long userIdCounter = 1L;
    public User createUser(String name) {
        User user = new User(userIdCounter++, name);
        users.put(user.getId(), user);
        return user;
    }
    public User getUser(Long userId) {
        return users.get(userId);
    }
}
