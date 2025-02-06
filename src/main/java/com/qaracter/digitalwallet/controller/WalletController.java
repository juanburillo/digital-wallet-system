package com.qaracter.digitalwallet.controller;

import com.qaracter.digitalwallet.model.Currency;
import com.qaracter.digitalwallet.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(summary = "Create a new wallet for a user",
            description = "Creates a wallet with the specified currency and balance for a given user.")
    @PostMapping
    public String createWallet(@RequestParam
                               @Schema(description = "The user ID for whom the wallet is created", example = "1") Long userId,

                               @RequestParam
                               @Schema(description = "The currency type for the wallet", example = "EUR") Currency currency,

                               @RequestParam
                               @Schema(description = "The initial balance for the wallet", example = "1000.0") Double balance) {
        walletService.createWallet(userId, currency, balance);
        return "Wallet added successfully";
    }
}
