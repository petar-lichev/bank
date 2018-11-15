package com.bank.bg.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void transfer(Long sender_id, Long receiver_id, Double amount) throws Exception {

		Account sender = em.find(Account.class, sender_id);
		Account receiver = em.find(Account.class, receiver_id);

		if (sender == null || receiver == null) {
			throw new Exception("Invalid accounts");
		}
		if (sender.getAmount() < amount) {
			throw new Exception("Balance is insufficient.");
		}

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setSender(sender);
		transaction.setReceiver(receiver);
		transaction.setDate_time(LocalDateTime.now());

		sender.setAmount(sender.getAmount() - amount);
		if (true)
			throw new RuntimeException("tashak");
		receiver.setAmount(receiver.getAmount() + amount);

		em.persist(transaction);

	}
	
	
	public List<Transaction> getTransactionsInPeriod(LocalDate from, LocalDate to) {
		LocalDateTime from_LocalDateTime = from.atStartOfDay();
		LocalDateTime to_LocalDateTime = to.atStartOfDay();
		
		Query query = em.createQuery("SELECT tr from Transaction tr");
//		query.setParameter("from", from_LocalDateTime);
//		query.setParameter("to", to_LocalDateTime);
		
		return query.getResultList();
	
	}

}
