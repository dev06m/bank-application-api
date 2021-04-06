package com.account.transactions.exceptionhandler;

public class AccountErrorResponse {

    private int status;
    private boolean isError;
    private String message;

    public AccountErrorResponse() {}

    public AccountErrorResponse(boolean isError, String message) {
        this.isError = isError;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
