package com.bank.bg.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
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
//
//	@Autowired
//	PlatformTransactionManager transactionManager;

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void transfer(Long sender_id, Long receiver_id, Double amount) throws Exception {

//		em.merge(sender);
//		em.merge(receiver);

		Account sender = em.find(Account.class, sender_id);
		Account receiver = em.find(Account.class, receiver_id);
		
		if(sender == null || receiver == null) {
			throw new Exception("Invalid accounts");
		}
		if (sender.getAmount() < amount) {
			throw new Exception("Balance is insufficient.");
		}

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setSender(sender);
		transaction.setReceiver(receiver);

		sender.setAmount(sender.getAmount() - amount);
		if (true)
			throw new RuntimeException("tashak");
		receiver.setAmount(receiver.getAmount() + amount);

		
		em.persist(transaction);


	}

}
