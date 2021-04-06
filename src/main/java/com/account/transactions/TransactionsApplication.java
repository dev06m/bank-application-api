package com.account.transactions;

import com.account.transactions.dao.AccountDao;
import com.account.transactions.model.Account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionsApplication.class, args);

	}

	@Bean
	CommandLineRunner runner(AccountDao accountDao) {
		return args -> {
			accountDao.save(new Account("Amy Adams","TRY", 100));
			accountDao.save(new Account("Jeremy Renner","USD", 300));
			accountDao.save(new Account("Forest Whitaker","USD", 1000));
			accountDao.save(new Account("Michael Stuhibarg","TRY", 650));
			accountDao.save(new Account("Julia Scarlett","EUR", 25000));
			accountDao.save(new Account("Tzi Ma","TRY", 10));
			accountDao.save(new Account("Eric Heisserer","EUR", 5000));
			accountDao.save(new Account("Patrick Kiely","EUR", 500));
		};
	}
}
