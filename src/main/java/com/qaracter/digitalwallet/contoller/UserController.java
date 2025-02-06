package com.qaracter.digitalwallet.contoller;

import com.qaracter.digitalwallet.model.User;
import com.qaracter.digitalwallet.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user.getName());
    }
}