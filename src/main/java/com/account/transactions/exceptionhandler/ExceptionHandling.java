package com.account.transactions.exceptionhandler;

import com.account.transactions.controller.InsufficientBalanceError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler
    public ResponseEntity<AccountErrorResponse> handleException(Exception exp) {

        AccountErrorResponse error = new AccountErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exp.getMessage());
        error.setError(true);

        return  new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AccountErrorResponse> insufficientError(InsufficientBalanceError exp) {

        AccountErrorResponse error = new AccountErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exp.getMessage());
        error.setError(true);

        return  new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AccountErrorResponse> currencyError(CurrencyCodeNotMatchError exp) {

        AccountErrorResponse error = new AccountErrorResponse();

        error.setStatus(HttpStatus.CONFLICT.value());
        error.setMessage(exp.getMessage());
        error.setError(true);

        return  new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
