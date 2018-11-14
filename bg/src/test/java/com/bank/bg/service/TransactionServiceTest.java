package com.bank.bg.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.bank.bg.BgApplicationTests;
import com.bank.bg.model.Account;
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
		user1 = new User();
		user1.setUsername("Gosho");
		user1.setEmail("lele@abv.bg");

		user2 = new User();
		user2.setUsername("Tosho");
		user2.setEmail("lele2@abv.bg");

		em.persist(user1);
		em.persist(user2);

		account1 = new Account();
		account1.setAmount((double) 1000);
		account1.setUser(user1);

		account2 = new Account();
		account1.setUser(user2);
		account2.setAmount((double) 1000);
		
		em.persist(account1);
		em.persist(account2);
	}

	@Test
	public void transferTest() throws Exception {
		try {
		transaction_service.transfer(account1, account2, (double)500);
		}finally {
		Query query1 = em.createQuery("select a from Account a where a = :account");
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
		//assertTrue(acc_new1.getAmount() == 500);
		//assertTrue(acc_new2.getAmount() == 1500);
		}
	}

}
