package com.qaracter.digitalwallet.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a transaction within the digital wallet system.
 */
public class Transaction {

    private String transactionId;
    private String senderWalletId;
    private String recipientWalletId;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime timestamp;
    private BigDecimal exchangeRate;

    public Transaction(String transactionId, String senderWalletId, String recipientWalletId, BigDecimal amount, String currency, LocalDateTime timestamp, BigDecimal exchangeRate) {
        this.transactionId = transactionId;
        this.senderWalletId = senderWalletId;
        this.recipientWalletId = recipientWalletId;
        this.amount = amount;
        this.currency = currency;
        this.timestamp = timestamp;
        this.exchangeRate = exchangeRate;
    }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId;}

    public String getSenderWalletId() { return senderWalletId; }
    public void setSenderWalletId(String senderWalletId) { this.senderWalletId = senderWalletId; }

    public String getRecipientWalletId() { return recipientWalletId; }
    public void setRecipientWalletId(String recipientWalletId) { this.recipientWalletId = recipientWalletId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public BigDecimal getExchangeRate() { return exchangeRate; }
    public void setExchangeRate(BigDecimal exchangeRate) { this.exchangeRate = exchangeRate; }

}
