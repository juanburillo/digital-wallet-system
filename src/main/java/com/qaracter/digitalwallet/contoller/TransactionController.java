package com.qaracter.digitalwallet.contoller;

import com.qaracter.digitalwallet.common.exception.InsufficientFundsException;
import com.qaracter.digitalwallet.model.Transaction;
import com.qaracter.digitalwallet.service.TransactionService;
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
     * @param senderUserId     the ID of the sender user
     * @param senderCurrency   the currency of the sender's wallet
     * @param recipientUserId  the ID of the recipient user
     * @param recipientCurrency the currency of the recipient's wallet
     * @param amount           the amount to transfer
     * @param exchangeRate     the exchange rate to be used (optional)
     * @return the created Transaction object
     * @throws InsufficientFundsException if the sender has insufficient funds
     */
    @PostMapping
    public Transaction createTransaction(
            @RequestParam Long senderUserId,
            @RequestParam String senderCurrency,
            @RequestParam Long recipientUserId,
            @RequestParam String recipientCurrency,
            @RequestParam BigDecimal amount,
            @RequestParam(required = false) BigDecimal exchangeRate) throws InsufficientFundsException {
        return transactionService.createTransaction(senderUserId, senderCurrency, recipientUserId, recipientCurrency, amount, exchangeRate);
    }

    /**
     * Retrieves the transaction history for a given wallet.
     *
     * @param walletId the ID of the wallet
     * @return a list of transactions associated with the wallet
     */
    @GetMapping("/{walletId}")
    public List<Transaction> getTransactionHistory(@PathVariable String walletId) {
        return transactionService.getTransactionHistory(walletId);
    }
}