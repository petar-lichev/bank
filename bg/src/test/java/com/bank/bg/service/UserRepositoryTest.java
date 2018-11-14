package com.bank.bg.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bank.bg.BgApplicationTests;
import com.bank.bg.model.User;

public class UserRepositoryTest extends BgApplicationTests {
	
	@Autowired
	UserRepository user_repo;
	
	@Test
	public void findByUsernameTest() {
		User u = new User();
		u.setUsername("gosho");
		u.setEmail("lele");
		
		user_repo.save(u);
		User u2 = user_repo.findByUsername("gosho");
		
		assertTrue(u.equals(u2));
	}
	
	
	@Test
	public void findByEmailTest() {
		User u = new User();
		u.setUsername("gosho");
		u.setEmail("lele");
		
		user_repo.save(u);
		User u2 = user_repo.findByEmail("lele");
		
		assertTrue(u.equals(u2));
	}


}
