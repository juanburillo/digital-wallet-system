package com.qaracter.digitalwallet;

import com.qaracter.digitalwallet.model.Currency;
import com.qaracter.digitalwallet.model.User;
import com.qaracter.digitalwallet.service.UserService;
import com.qaracter.digitalwallet.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WalletTest {

    private WalletService walletService;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = mock(UserService.class);
        walletService = new WalletService(userService);
    }

    @Test
    public void testCreateWallet() {
        User user = new User(1L, "MR JAIME DOE");
        when(userService.getUser(1L)).thenReturn(user);

        walletService.createWallet(1L, Currency.USD, 1000.0);

        assertNotNull(user.getWallets().get(Currency.USD));
        assertEquals(1000.0, user.getWallets().get(Currency.USD));
        verify(userService, times(1)).getUser(1L);
    }

    @Test
    public void testCreateWalletForNonExistingUser() {
        when(userService.getUser(1L)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            walletService.createWallet(1L, Currency.USD, 1000.0);
        });

        verify(userService, times(1)).getUser(1L);
    }
}