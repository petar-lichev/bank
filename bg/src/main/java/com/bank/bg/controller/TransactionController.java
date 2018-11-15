package com.bank.bg.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bank.bg.model.Account;
import com.bank.bg.service.AccountRepository;
import com.bank.bg.service.TransactionRepository;
import com.bank.bg.service.TransactionService;

@Controller
public class TransactionController {
	
	@Autowired
	TransactionRepository transaction_repo;
	
	@Autowired
	AccountRepository account_repo;
	
	@Autowired
	TransactionService transaction_service;
	
	@RequestMapping(value = "/lele", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity lele() throws Exception {
		
		Optional<Account> sender = account_repo.findById(15L);
		Optional<Account> receiver = account_repo.findById(25L);
		
		transaction_service.transfer(sender.get().getId(), receiver.get().getId(), (double)500);
		
		return new ResponseEntity("dasdasdas", HttpStatus.OK);
		
	}


}
