package com.bank.bg.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bank.bg.model.Account;
import com.bank.bg.model.Transaction;

@Service
public class TransactionService {

	@Autowired
	protected EntityManagerFactory factory;

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	PlatformTransactionManager transactionManager;

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void transfer(Account sender, Account receiver, Double amount) throws Exception {
		 TransactionTemplate template = new TransactionTemplate(transactionManager);
		 template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	      template.execute(new TransactionCallbackWithoutResult() {
	          @Override
	          protected void doInTransactionWithoutResult(TransactionStatus status) {
	              try {
	          		if (sender.getAmount() < amount) {
	        			throw new Exception("Balance is insufficient.");
	        		}

	        		EntityManager entity_manager = factory.createEntityManager();
	        		//entity_manager.getTransaction().begin();

	        		Transaction transaction = new Transaction();
	        		transaction.setAmount(amount);
	        		transaction.setSender(sender);
	        		transaction.setReceiver(receiver);

	        		sender.setAmount(sender.getAmount() - amount);
	        		if(true) throw new Exception("tashak");
	        		receiver.setAmount(receiver.getAmount() + amount);

//	        		entity_manager.merge(sender);
//	        		entity_manager.merge(receiver);
	        		entity_manager.persist(transaction);
	            	  
	              } catch (Exception e) {
	            	  
	            	  System.out.println("laina");
	                  
	              }
	          }
	      });


		//entity_manager.getTransaction().commit();

	}

}
