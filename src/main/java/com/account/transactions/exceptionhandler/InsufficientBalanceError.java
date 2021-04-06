package com.account.transactions.controller;

public class InsufficientBalanceError extends RuntimeException {

    public InsufficientBalanceError() {
    }

    public InsufficientBalanceError(String message) {
        super(message);
    }

    public InsufficientBalanceError(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientBalanceError(Throwable cause) {
        super(cause);
    }

    public InsufficientBalanceError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
