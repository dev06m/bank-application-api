package com.account.transactions.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
//@Data
public class Transaction {


    @Id @GeneratedValue
    int transactionId;
    int senderAccountNumber;
    int receiverAccountNumber;
    double amount;
    boolean isTransactionSuccessful;

    public Transaction(int senderAccountNumber, int receiverAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.isTransactionSuccessful = false;
    }

    public Transaction() { }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(int senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public int getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(int receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isTransactionSuccessful() {
        return isTransactionSuccessful;
    }

    public void setTransactionSuccessful(boolean transactionSuccessful) {
        isTransactionSuccessful = transactionSuccessful;
    }


}
