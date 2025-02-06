package com.qaracter.digitalwallet.service;

import com.qaracter.digitalwallet.model.User;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    private final UserService userService;

    public WalletService(UserService userService) {
        this.userService = userService;
    }

    public void createWallet(Long userId, String currency, Double balance) {
        User user = userService.getUser(userId);
        if (user != null) {
            user.getWallets().put(currency, balance);
        }
    }
}
