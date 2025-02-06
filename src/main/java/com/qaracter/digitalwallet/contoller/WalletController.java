package com.qaracter.digitalwallet.contoller;

import com.qaracter.digitalwallet.service.WalletService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    private final WalletService walletService;
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }
    @PostMapping
    public String createWallet(@RequestParam Long userId, @RequestParam String currency, @RequestParam Double balance) {
        walletService.createWallet(userId, currency, balance);
        return "Wallet added successfully";
    }
}
