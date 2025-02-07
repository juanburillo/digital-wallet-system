package com.qaracter.digitalwallet.controller;

import com.qaracter.digitalwallet.common.exception.InsufficientFundsException;
import com.qaracter.digitalwallet.model.Currency;
import com.qaracter.digitalwallet.model.Transaction;
import com.qaracter.digitalwallet.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * REST controller for managing transactions.
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    /**
     * Constructs a new TransactionController with the specified TransactionService.
     *
     * @param transactionService the transaction service to be used by this controller
     */
    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Creates a new transaction.
     *
     * @param senderUserId      the ID of the sender user
     * @param senderCurrency    the currency of the sender's wallet
     * @param recipientUserId   the ID of the recipient user
     * @param recipientCurrency the currency of the recipient's wallet
     * @param amount            the amount to transfer
     * @param exchangeRate      the exchange rate to be used (optional)
     * @return the created Transaction object
     * @throws InsufficientFundsException if the sender has insufficient funds
     */
    @Operation(summary = "Create a new transaction",
            description = "Creates a transaction between two users, transferring a specified amount in the respective currencies.")
    @PostMapping
    public Transaction createTransaction(@RequestParam
                                         @Schema(description = "The ID of the sender user", example = "1") Long senderUserId,

                                         @RequestParam
                                         @Schema(description = "The currency of the sender's wallet", example = "USD") Currency senderCurrency,

                                         @RequestParam
                                         @Schema(description = "The ID of the recipient user", example = "2") Long recipientUserId,

                                         @RequestParam
                                         @Schema(description = "The currency of the recipient's wallet", example = "EUR") Currency recipientCurrency,

                                         @RequestParam
                                         @Schema(description = "The amount to transfer", example = "100.00") BigDecimal amount,

                                         @RequestParam(required = false)
                                         @Schema(description = "The exchange rate to be applied if currencies are different") BigDecimal exchangeRate) throws InsufficientFundsException {
        return transactionService.createTransaction(senderUserId, senderCurrency, recipientUserId, recipientCurrency, amount, exchangeRate);
    }

    /**
     * Retrieves the transaction history for a given wallet.
     *
     * @param walletId the ID of the wallet
     * @return a list of transactions associated with the wallet
     */
    @Operation(summary = "Retrieve transaction history for a wallet",
            description = "Fetches a list of transactions associated with the provided wallet ID.")
    @GetMapping("/{walletId}")
    public List<Transaction> getTransactionHistory(@PathVariable
                                                   @Schema(description = "The wallet ID for which transaction history is requested", example = "1-USD") String walletId) {
        return transactionService.getTransactionHistory(walletId);
    }

}