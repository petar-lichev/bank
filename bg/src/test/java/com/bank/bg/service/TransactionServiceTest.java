package com.bank.bg.service;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bank.bg.BgApplicationTests;
import com.bank.bg.model.Account;
import com.bank.bg.model.Transaction;
import com.bank.bg.model.User;

public class TransactionServiceTest extends BgApplicationTests {
	protected static User user1;
	protected static User user2;

	protected static Account account1;
	protected static Account account2;
	

	@Autowired
	protected TransactionService transaction_service;

	@Before
	public void initialSetup() {
//		EntityManager em2 = factory.createEntityManager();
//		em2.getTransaction().begin();
		user1 = new User();
		user1.setUsername("Gosho");
		user1.setEmail("lele@abv.bg");

		user2 = new User();
		user2.setUsername("Tosho");
		user2.setEmail("lele2@abv.bg");

		em.persist(user1);
		em.persist(user2);
		em.flush();
		account1 = new Account();
		account1.setAmount((double) 1000);
		account1.setUser(user1);

		account2 = new Account();
		account1.setUser(user2);
		account2.setAmount((double) 1000);

		em.persist(account1);
		em.persist(account2);
		em.flush();
		Transaction transaction1 = new Transaction();
		transaction1.setAmount((double)100);
		transaction1.setSender(account1);
		transaction1.setReceiver(account2);
		transaction1.setDate_time(LocalDateTime.now());
		
		Transaction transaction2 = new Transaction();
		transaction2.setAmount((double)100);
		transaction2.setSender(account2);
		transaction2.setReceiver(account1);
		transaction2.setDate_time(LocalDateTime.now().minusHours(2));
		
		Transaction transaction3 = new Transaction();
		transaction3.setAmount((double)100);
		transaction3.setSender(account1);
		transaction3.setReceiver(account2);
		transaction3.setDate_time(LocalDateTime.now().minusHours(4));
		
		Transaction transaction4 = new Transaction();
		transaction4.setAmount((double)100);
		transaction4.setSender(account2);
		transaction4.setReceiver(account1);
		transaction4.setDate_time(LocalDateTime.now().minusHours(8));

		em.persist(transaction1);
		em.persist(transaction2);
		em.persist(transaction3);
		em.persist(transaction4);

		em.flush();
//		em2.getTransaction().commit();
		em.close();
//		em.flush();
	}
	
	@After
	public void deleteAll() {
		EntityManager em2 = factory.createEntityManager();
		em2.getTransaction().begin();
		
		Query delete_transactions = em2.createQuery("DELETE FROM Transaction");
		delete_transactions.executeUpdate();
		Query delete_accounts = em2.createQuery("DELETE FROM Account");
		delete_accounts.executeUpdate();
		Query delete_users = em2.createQuery("DELETE FROM User");
		delete_users.executeUpdate();
		
		em2.getTransaction().commit();
	}

	@Test
	@Transactional()
	public void transferTest() throws Exception {

		try {
			transaction_service.transfer(account1.getId(), account2.getId(), (double) 500);
		} catch (Exception e) {
		}

		Query query1 = em.createQuery("select a from Account a where a = :account", Account.class);
		query1.setParameter("account", account1);
		List<Account> list_acc = query1.getResultList();
		Account acc_new1 = list_acc.get(0);

		query1.setParameter("account", account2);
		list_acc = query1.getResultList();
		Account acc_new2 = list_acc.get(0);
		System.out.println(acc_new1.getAmount());
		System.out.println(acc_new2.getAmount());

		assertTrue(acc_new1.getAmount() == 1000);
		assertTrue(acc_new2.getAmount() == 1000);
	}
	
	
	@Test
	public void checkTransactionsBetweenTwoDates() {
		
		List<Transaction> transactions_list = transaction_service.getTransactionsInPeriod(LocalDateTime.now().toLocalDate().minusDays(2), LocalDateTime.now().toLocalDate());
		
		System.out.println("Transactions found: " + transactions_list.size());
		assertTrue(transactions_list.size() == 4);
		for(Transaction tr: transactions_list) {
			System.out.println(tr);
			assertTrue(tr.getDate_time().toLocalDate().equals(LocalDate.now()));
		}
		
		
		
		
	}
	
	
	

}
