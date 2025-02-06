package com.qaracter.digitalwallet.controller;

import com.qaracter.digitalwallet.model.User;
import com.qaracter.digitalwallet.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Retrieve a user by ID",
            description = "Fetches the user details based on the provided user ID.")
    @GetMapping("/{userId}")
    public User getUser(@PathVariable
                        @Schema(description = "The ID of the user", example = "1") Long userId) {
        return userService.getUser(userId);
    }

    @Operation(summary = "Create a new user",
            description = "Creates a new user with the provided name.")
    @PostMapping
    public User createUser(@RequestBody
                           @Schema(description = "User object containing the name", example = "{\"name\": \"John Doe\"}") User user) {
        return userService.createUser(user.getName());
    }
}