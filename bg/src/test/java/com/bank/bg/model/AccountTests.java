package com.bank.bg.model;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.bank.bg.BgApplicationTests;

public class AccountTests extends BgApplicationTests {

	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
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
	
	
	@SuppressWarnings("unchecked")
	@Test
	@Transactional
	public void integrityConstraints() {
		Account a1 = new Account();
		em.persist(a1);

		Query queryA = em.createQuery("SELECT a.id FROM Account a");

		List<Long> bla = queryA.getResultList();
		for (Long o : bla) {
		}
		assertTrue(bla.contains(1L));

	}
	
	public void referentialIntegrityConstraints() {
		try {
			EntityManager em = factory.createEntityManager();
			
			em.getTransaction().begin();
			User u1 = new User();
			u1.setUsername("SAME_USERNAME");
			u1.setEmail("dsadad");
			em.persist(u1);

			em.getTransaction().commit();
			
			em.close();
		} catch (Exception e) {
			assertTrue(e.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException);

		}
	}
	
	
	

}
