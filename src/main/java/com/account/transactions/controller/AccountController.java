package com.account.transactions.controller;

import com.account.transactions.dao.AccountDao;
import com.account.transactions.dao.TransactionDao;
import com.account.transactions.exceptionhandler.CurrencyCodeNotMatchError;
import com.account.transactions.model.Account;
import com.account.transactions.model.Response;
import com.account.transactions.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bank")
public class AccountController {

    /*
    *   Endpointler:
    *   tüm banka hesapları --> localhost:8080/bank/accounts (GET request)
    *   tüm banka işlemlerii --> localhost:8080/bank/transactions
    *   yeni hesap oluşturma --> localhost:8080/bank/accounts  (POST request)
    *   para transferı      --> localhost:8080/bank/transactions
    *
    *   örnek yeni hesap body:
                             {
                            "fullName": "Full Name",
                            "currencyCode": "EUR",
                            "balance": 10.45885
                              }
                              *
    *   örnek para transferi body:
                            {
                            "senderAccountNumber": 1,
                            "receiverAccountNumber": 2,
                            "amount": 5
                            }
     */

    @Autowired
    AccountDao accountDao;
    @Autowired
    TransactionDao transactionDao;

    @ResponseBody
    @GetMapping("/accounts")
    public List<Account> fetchAccounts() {
        return accountDao.findAll();
    }

    @GetMapping("/transactions")
    public List<Transaction> fetchTransactions() {

        return transactionDao.findAll();
    }


    @ResponseBody
    @PostMapping("/accounts")
    public Response save(@RequestBody Account account) throws Exception {
        boolean fullNameValidation = (account.getFullName().trim().isEmpty()) || (account.getFullName() == null);
        boolean currencyCodeValidation = !(account.getCurrencyCode().equals("TRY") || account.getCurrencyCode().equals("USD") || account.getCurrencyCode().equals("EUR"));
        if (fullNameValidation) {
            throw new Exception("Full Name is not valid!");
        } else if (currencyCodeValidation) {
            throw new Exception("Currency code can be only TRY, USD, or EUR");
        }else{
            account.setBalance((Math. round(account.getBalance()*100))/100d);
            accountDao.save(account);
        }
        return new Response(false, account.getCurrencyCode());
    }


    @PostMapping("/transactions")
    public Response transferMoney(@RequestBody Transaction transaction) throws Exception {

        // get sender and receiver accounts
        Optional<Account> senderAccount = accountDao.findById(transaction.getSenderAccountNumber());
        Optional<Account> receiverAccount = accountDao.findById(transaction.getReceiverAccountNumber());

        // check if account numbers valid
        if(!senderAccount.isPresent() || !receiverAccount.isPresent()) {
            transaction.setTransactionSuccessful(false);
            transactionDao.save(transaction);
            throw new Exception("Account Number(s) not valid");
        }
        // check if both accounts have same currency code
        if (!senderAccount.get().getCurrencyCode().equals(receiverAccount.get().getCurrencyCode())) {
            transaction.setTransactionSuccessful(false);
            transactionDao.save(transaction);
            throw new CurrencyCodeNotMatchError("Currency codes not match! " +
                       "Sender account: " + senderAccount.get().getCurrencyCode() +
                        " and " +
                        "Receiver account: " + receiverAccount.get().getCurrencyCode());
        }
        // check if balance is available
       if (senderAccount.get().getBalance() - transaction.getAmount() < 0) {
           transaction.setTransactionSuccessful(false);
           transactionDao.save(transaction);
           throw  new com.account.transactions.controller.InsufficientBalanceError("Insufficient balance!");
       }

        // if conditions are okay, complete money transfer
        senderAccount.get().sendMoney(transaction.getAmount());
        receiverAccount.get().addMoney(transaction.getAmount());
        accountDao.save(senderAccount.get());
        accountDao.save(receiverAccount.get());
        transaction.setTransactionSuccessful(true);
        transactionDao.save(transaction);


        return new Response(false, senderAccount.get().getCurrencyCode());
    }


}
