package com.bank.bg.model;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import com.bank.bg.BgApplicationTests;

public class UserTests extends BgApplicationTests {

	@Autowired
	JdbcTemplate jdbcTemplate;
	

	@SuppressWarnings({ "unlikely-arg-type", "unchecked" })
	@Test
	public void userModelTest() {

		Query tables_query = em.createNativeQuery("SELECT table_name FROM information_schema.tables;");

		Query columns_query = em
				.createNativeQuery("SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='USER';");

		List<Object[]> tables_list = tables_query.getResultList();
		List<Object[]> user_columns = columns_query.getResultList();

		assertTrue(tables_list.contains("USER"));

		assertTrue(user_columns.contains("ID"));
		assertTrue(user_columns.contains("USERNAME"));
		assertTrue(user_columns.contains("EMAIL"));

	}

	@Test
	public void notNullUsernameConstraint() {
		boolean flag = false;
		try {
			String insert_null_query = "insert into user(id, username, email) values(:id, null, 'dsd')";
			Query q = em.createNativeQuery(insert_null_query);
			q.setParameter("id", 222L);
			q.executeUpdate();

		} catch (PersistenceException e) {
			
			flag = true;
			assertTrue(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException);

		}
		
		assertTrue(flag);

	}

	@Test
	public void notNullEmailConstraint() {
		boolean flag = false;

		try {
			String insert_null_query = "insert into user(id, username, email) values(:id, 'ddsds', null)";
			Query q = em.createNativeQuery(insert_null_query);
			q.setParameter("id", 212L);
			q.executeUpdate();

		} catch (PersistenceException e) {
			flag = true;
			assertTrue(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException);
		}
		
		assertTrue(flag);

	}

	// problem
	@SuppressWarnings("unchecked")
	@Test
	@Rollback(false)
	public void uniqueUsernameConstraint() {
		try {
			EntityManager em = factory.createEntityManager();
			
			em.getTransaction().begin();
			User u1 = new User();
			u1.setUsername("SAME_USERNAME");
			u1.setEmail("dsadad");
			em.persist(u1);

			User u3 = new User();
			u3.setUsername("SAME_USERNAME");
			u3.setEmail("aaaa");

			em.persist(u3);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			assertTrue(e.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException);

		}

	}
	
	
	
	@SuppressWarnings("unchecked")
	@Test
	@Rollback(false)
	public void uniqueEmailConstraint() {
		boolean flag = false;
		try {
			EntityManager em = factory.createEntityManager();
			
			em.getTransaction().begin();
			User u1 = new User();
			u1.setUsername("USERNAME1");
			u1.setEmail("same_email");
			em.persist(u1);

			User u3 = new User();
			u3.setUsername("USERNAME2");
			u3.setEmail("same_email");

			em.persist(u3);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			flag = true;
			assertTrue(e.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException);

		}
		
		assertTrue(flag);

	}
	
	@Test
	public void equalsTest() {
		User u1 = new User();
		User u2 = new User();
		u1.setId(1L);
		u2.setId(1L);
		u1.setUsername("ddd");
		u2.setUsername("ddd");
		u1.setEmail("bla");
		u2.setEmail("bla");
		
		assertTrue(u1 != u2);
		assertTrue(u1.equals(u2));
	}
	

}
