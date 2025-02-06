package com.qaracter.digitalwallet.service;

import com.qaracter.digitalwallet.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TransactionStorage {

    private final Map<String, Transaction> transactions = new HashMap<>();

    /**
     * Saves a transaction.
     *
     * @param transaction The transaction to save.
     */
    public void save(Transaction transaction) {
        transactions.put(transaction.getTransactionId(), transaction);
    }

    /**
     * Retrieves a transaction by its ID.
     *
     * @param transactionId The ID of the transaction.
     * @return The Transaction object, or null if not found.
     */
    public Transaction getTransactionById(String transactionId) {
        return transactions.get(transactionId);
    }

    /**
     * Retrieves all transactions associated with a wallet (either sender or recipient).
     *
     * @param walletId The ID of the wallet.
     * @return A list of transactions.
     */
    public List<Transaction> getTransactionsByWalletId(String walletId) {
        List<Transaction> transactionList = new ArrayList<>();
        for (Transaction transaction : transactions.values()) {
            if (transaction.getSenderWalletId().equals(walletId) || transaction.getRecipientWalletId().equals(walletId)) {
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }
}
