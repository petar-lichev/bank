package com.bank.bg.model;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.Query;

import org.junit.Test;

import com.bank.bg.BgApplicationTests;

public class AccountTests extends BgApplicationTests {

	@Test
	public void accountModelTest() {
//		
//		Query tables_query = em
//				.createNativeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'bank_bg';");
//
//		Query columns_query = em.createNativeQuery(
//				"SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='bank_bg' AND TABLE_NAME='Account';");

		Query tables_query = em.createNativeQuery("SELECT table_name FROM information_schema.tables;");

		Query columns_query = em
				.createNativeQuery("SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='ACCOUNT';");

		List<Object[]> tables_list = tables_query.getResultList();
		List<Object[]> account_columns = columns_query.getResultList();

		assertTrue(tables_list.contains("ACCOUNT"));

		assertTrue(account_columns.contains("ID"));

	}
	
	
	@Test
	public void integrityConstraints() {
		Account a1 = new Account();
		em.persist(a1);

		Query queryA = em.createQuery("SELECT a.id FROM Account a");

		List<Long> bla = queryA.getResultList();
		for (Long o : bla) {
			System.out.println(o);
			System.out.println(o.equals(1L));
		}
		assertTrue(bla.contains(1L));

	}

}
