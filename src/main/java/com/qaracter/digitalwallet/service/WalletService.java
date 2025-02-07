package com.qaracter.digitalwallet.service;

import com.qaracter.digitalwallet.model.Currency;
import com.qaracter.digitalwallet.model.User;
import org.springframework.stereotype.Service;

/**
 * Contains the business logic for managing wallets in the system.
 */
@Service
public class WalletService {

    private final UserService userService;

    public WalletService(UserService userService) {
        this.userService = userService;
    }

    public void createWallet(Long userId, Currency currency, Double balance) {
        User user = userService.getUser(userId);
        if (user != null) { // If user exists
            user.getWallets().put(currency, balance);
        }
    }
    
}
