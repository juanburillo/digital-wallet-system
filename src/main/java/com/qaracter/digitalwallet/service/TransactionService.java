package com.qaracter.digitalwallet.service;

import com.qaracter.digitalwallet.common.exception.InsufficientFundsException;
import com.qaracter.digitalwallet.model.Currency;
import com.qaracter.digitalwallet.model.Transaction;
import com.qaracter.digitalwallet.model.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Service class for managing transactions.
 */
@Service
public class TransactionService {

    private final UserService userService;
    private final TransactionStorage transactionStorage;
    private final CurrencyConversionService currencyConversionService;

    /**
     * Constructs a new TransactionService with the specified dependencies.
     *
     * @param userService the user service to be used by this service
     * @param transactionStorage the transaction storage to be used by this service
     * @param currencyConversionService the currency conversion service to be used by this service
     */
    public TransactionService(UserService userService, TransactionStorage transactionStorage, CurrencyConversionService currencyConversionService) {
        this.userService = userService;
        this.transactionStorage = transactionStorage;
        this.currencyConversionService = currencyConversionService;
    }

    /**
     * Creates a new transaction between two users.
     *
     * @param senderUserId the ID of the sender user
     * @param senderCurrency the currency of the sender's wallet
     * @param recipientUserId the ID of the recipient user
     * @param recipientCurrency the currency of the recipient's wallet
     * @param amount the amount to transfer
     * @param exchangeRate the exchange rate to be used (optional)
     * @return the created Transaction object
     * @throws InsufficientFundsException if the sender has insufficient funds
     * @throws IllegalArgumentException if the user IDs or currencies are invalid, or if the exchange rate is missing for cross-currency transactions
     */
    public Transaction createTransaction(Long senderUserId, String senderCurrency, Long recipientUserId, String recipientCurrency, BigDecimal amount, BigDecimal exchangeRate) throws InsufficientFundsException {

        User sender = userService.getUser(senderUserId);
        User recipient = userService.getUser(recipientUserId);

        if (sender == null || recipient == null) {
            throw new IllegalArgumentException("Invalid user IDs");
        }

        Map<String, Double> senderWallets = sender.getWallets();
        Map<String, Double> recipientWallets = recipient.getWallets();

        if (!senderWallets.containsKey(senderCurrency) || !recipientWallets.containsKey(recipientCurrency)) {
            throw new IllegalArgumentException("Invalid currency for user");
        }

        double senderBalance = senderWallets.get(senderCurrency);

        if (BigDecimal.valueOf(senderBalance).compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds in sender's wallet");
        }

        BigDecimal convertedAmount = amount;
        if (!senderCurrency.equals(recipientCurrency)) {
            if (exchangeRate == null) {
                throw new IllegalArgumentException("Exchange rate is required for cross-currency transactions");
            }
            convertedAmount = BigDecimal.valueOf(currencyConversionService.convert(
                    Currency.valueOf(senderCurrency),
                    Currency.valueOf(recipientCurrency),
                    amount.doubleValue()
            ));
        }

        senderWallets.put(senderCurrency, senderBalance - amount.doubleValue());
        recipientWallets.put(recipientCurrency, recipientWallets.get(recipientCurrency) + convertedAmount.doubleValue());

        String transactionId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(transactionId, senderUserId.toString() + "-" + senderCurrency, recipientUserId.toString() + "-" + recipientCurrency, amount, senderCurrency, LocalDateTime.now(), exchangeRate);
        transactionStorage.save(transaction);

        return transaction;
    }

    /**
     * Retrieves transaction history for a given wallet.
     *
     * @param walletId ID of the wallet.
     * @return A list of transactions associated with the wallet.
     */
    public List<Transaction> getTransactionHistory(String walletId) {
        return transactionStorage.getTransactionsByWalletId(walletId);
    }
}