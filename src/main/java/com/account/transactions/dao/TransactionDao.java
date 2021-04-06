package com.account.transactions.dao;

import com.account.transactions.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao extends JpaRepository<Transaction, Integer> {
}
