package com.qaracter.digitalwallet;

import com.qaracter.digitalwallet.common.exception.InsufficientFundsException;
import com.qaracter.digitalwallet.model.Currency;
import com.qaracter.digitalwallet.model.Transaction;
import com.qaracter.digitalwallet.model.User;
import com.qaracter.digitalwallet.service.TransactionService;
import com.qaracter.digitalwallet.service.UserService;
import com.qaracter.digitalwallet.service.TransactionStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionTest {

    private TransactionService transactionService;
    private UserService userService;
    private TransactionStorage transactionStorage;

    @BeforeEach
    public void setUp() {
        userService = mock(UserService.class);
        transactionStorage = mock(TransactionStorage.class);
        transactionService = new TransactionService(userService, transactionStorage, null);
    }

    @Test
    public void testCreateTransaction() throws InsufficientFundsException {
        User sender = new User(1L, "MR JAIME DOE");
        User recipient = new User(2L, "MR JUAN DOE");

        Map<Currency, Double> senderWallets = new HashMap<>();
        senderWallets.put(Currency.USD, 1000.0);
        sender.getWallets().putAll(senderWallets);

        Map<Currency, Double> recipientWallets = new HashMap<>();
        recipientWallets.put(Currency.USD, 500.0);
        recipient.getWallets().putAll(recipientWallets);

        when(userService.getUser(1L)).thenReturn(sender);
        when(userService.getUser(2L)).thenReturn(recipient);

        Transaction transaction = transactionService.createTransaction(1L, Currency.USD, 2L, Currency.USD, BigDecimal.valueOf(100), null);

        assertNotNull(transaction);
        assertEquals("1-USD", transaction.getSenderWalletId());
        assertEquals("2-USD", transaction.getRecipientWalletId());
        assertEquals(BigDecimal.valueOf(100), transaction.getAmount());
        assertEquals(Currency.USD, transaction.getCurrency());

        verify(userService, times(1)).getUser(1L);
        verify(userService, times(1)).getUser(2L);
        verify(transactionStorage, times(1)).save(transaction);
    }

//    @Test
//    public void testGetTransactionHistory() throws InsufficientFundsException {
//        User sender = new User(1L, "MR JAIME DOE");
//        User recipient = new User(2L, "MR JUAN DOE");
//
//        Map<Currency, Double> senderWallets = new HashMap<>();
//        senderWallets.put(Currency.USD, 1000.0);
//        sender.getWallets().putAll(senderWallets);
//
//        Map<Currency, Double> recipientWallets = new HashMap<>();
//        recipientWallets.put(Currency.USD, 500.0);
//        recipient.getWallets().putAll(recipientWallets);
//
//        when(userService.getUser(1L)).thenReturn(sender);
//        when(userService.getUser(2L)).thenReturn(recipient);
//
//        transactionService.createTransaction(1L, Currency.USD, 2L, Currency.USD, BigDecimal.valueOf(100), null);
//        // transactionService.createTransaction(1L, Currency.USD, 2L, Currency.USD, BigDecimal.valueOf(100), 1);
//
//        List<Transaction> transactions = transactionService.getTransactionHistory("1-USD");
//
//        assertNotNull(transactions);
//        assertFalse(transactions.isEmpty());
//        assertEquals(1, transactions.size());
//        assertEquals("1-USD", transactions.get(0).getSenderWalletId());
//    }
}