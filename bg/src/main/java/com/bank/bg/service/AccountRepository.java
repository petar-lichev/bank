package com.bank.bg.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bg.model.User;

public interface AccountRepository extends JpaRepository<User, Long>{

}
