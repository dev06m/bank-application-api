package com.account.transactions.model;

public class Response  {

    private boolean isError;
    private String currencyCode;

    public Response(boolean isError, String currencyCode) {
        this.isError = isError;
        this.currencyCode = currencyCode;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }


    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
