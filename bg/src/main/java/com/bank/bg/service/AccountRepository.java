package com.bank.bg.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bg.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
