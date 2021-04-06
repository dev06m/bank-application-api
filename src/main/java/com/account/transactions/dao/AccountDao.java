package com.account.transactions.dao;

import com.account.transactions.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao extends JpaRepository<Account, Integer> {


}
