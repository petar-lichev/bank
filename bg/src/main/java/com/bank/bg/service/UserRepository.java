package com.bank.bg.service;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.bg.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u from User u where username = :username")
	User findByUsername(@Param("username") String username);
	
	
	@Query("SELECT u from User u where email = :email")
	User findByEmail(@Param("email") String email);

}
