package com.qaracter.digitalwallet.common.exception;

/**
 * Exception thrown when a transaction cannot be completed due to insufficient funds.
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
