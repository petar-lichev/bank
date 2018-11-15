package com.bank.bg.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.bg.model.Account;
import com.bank.bg.model.Transaction;
import com.bank.bg.model.User;

@Service
public class TransactionService {

	@Autowired
	protected EntityManagerFactory factory;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	PlatformTransactionManager transactionManager;

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly=false)
	public void transfer(Account sender, Account receiver, Double amount) throws Exception {


		if (sender.getAmount() < amount) {
			throw new Exception("Balance is insufficient.");
		}

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setSender(sender);
		transaction.setReceiver(receiver);

		sender.setAmount(sender.getAmount() - amount);
//		if (true)
//			throw new RuntimeException("tashak");
		receiver.setAmount(receiver.getAmount() + amount);

		em.persist(transaction);


	}

}
