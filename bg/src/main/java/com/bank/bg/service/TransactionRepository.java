package com.bank.bg.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bg.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
