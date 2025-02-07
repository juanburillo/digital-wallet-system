package com.qaracter.digitalwallet;

import com.qaracter.digitalwallet.model.User;
import com.qaracter.digitalwallet.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void testCreateUser() {
        User user = userService.createUser("MR JAIME DO");

        assertNotNull(user);
        assertEquals("MR JAIME DO", user.getName());
        assertNotNull(user.getId());
    }

    @Test
    public void testGetUser() {
        User user = userService.createUser("MR JUAN DOE");
        User retrievedUser = userService.getUser(user.getId());

        assertNotNull(retrievedUser);
        assertEquals("MR JUAN DOE", retrievedUser.getName());
        assertEquals(user.getId(), retrievedUser.getId());
    }
}