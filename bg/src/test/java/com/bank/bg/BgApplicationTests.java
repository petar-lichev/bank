package com.bank.bg;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
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

}
