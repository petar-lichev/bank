package com.bank.bg;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.bg.service.TransactionServiceTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
public class BgApplicationTests {

	@Autowired
	protected EntityManagerFactory factory;
	
//	@PostConstruct
//    public void init() {
//        TransactionServiceTest.factory2 = factory;
//    }

	@PersistenceContext
	protected EntityManager em;


}
