package com.bank.bg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.bg.model.Account;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
public class BgApplicationTests {

	@PersistenceContext
	EntityManager em;

	@Autowired
	JdbcTemplate jdbc_template;

	@Test
	public void contextLoads() {
	}

	@Test
	public void accountModelTest() {
		Query tables_query = em
				.createNativeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'bank_bg';");

		Query columns_query = em.createNativeQuery(
				"SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='bank_bg' AND TABLE_NAME='Account';");

		List<Object[]> tables_list = tables_query.getResultList();
		List<Object[]> account_columns = columns_query.getResultList();

		assertTrue(tables_list.contains("account"));

		assertTrue(account_columns.contains("id"));

	}

	@Test
	public void userModelTest() {
		Query tables_query = em
				.createNativeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'bank_bg';");

		Query columns_query = em.createNativeQuery(
				"SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='bank_bg' AND TABLE_NAME='user';");

		List<Object[]> tables_list = tables_query.getResultList();
		List<Object[]> user_columns = columns_query.getResultList();

		assertTrue(tables_list.contains("user"));

		assertTrue(user_columns.contains("id"));
		assertTrue(user_columns.contains("username"));
		assertTrue(user_columns.contains("email"));

	}

	@Test
	public void notNullConstraint() {
		try {

			String insert_null_query = "insert into user(id, username, email) values(:id, null, 'dsd')";
			Query q = em.createNativeQuery(insert_null_query);
			q.setParameter("id", 222L);

			q.executeUpdate();
		} catch (PersistenceException e) {
			
			assertEquals("Column 'username' cannot be null", e.getCause().getCause().getMessage());

		}
		
		
		try {
			String insert_null_query = "insert into user(id, username, email) values(:id, 'ddsds', null)";
			Query q = em.createNativeQuery(insert_null_query);
			q.setParameter("id", 222L);
		}catch (PersistenceException e) {
			
			assertEquals("Column 'email' cannot be null", e.getCause().getCause().getMessage());

		}
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
