package com.account.transactions.exceptionhandler;

public class CurrencyCodeNotMatchError extends RuntimeException {

    public CurrencyCodeNotMatchError() {
    }

    public CurrencyCodeNotMatchError(String message) {
        super(message);
    }

    public CurrencyCodeNotMatchError(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrencyCodeNotMatchError(Throwable cause) {
        super(cause);
    }

    public CurrencyCodeNotMatchError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
