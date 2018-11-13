package com.bank.bg.model;

import static org.junit.Assert.assertTrue;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.junit.Test;
import com.bank.bg.BgApplicationTests;

public class UserTests extends BgApplicationTests {

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
	public void notNullUsernameConstraint(){

		try {
			String insert_null_query = "insert into user(id, username, email) values(:id, null, 'dsd')";
			Query q = em.createNativeQuery(insert_null_query);
			q.setParameter("id", 222L);
			q.executeUpdate();

		} catch (PersistenceException e) {

			assertTrue(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException);
			// assertEquals("Column 'username' cannot be null",

		}

	}

	@Test
	public void notNullEmailConstraint() {

		try {
			String insert_null_query = "insert into user(id, username, email) values(:id, 'ddsds', null)";
			Query q = em.createNativeQuery(insert_null_query);
			q.setParameter("id", 222L);
		} catch (PersistenceException e) {

			assertTrue(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException);
			// assertEquals("Column 'email' cannot be null",

		}
	}

}
