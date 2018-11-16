package com.bank.bg.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="sender_id")
	private Account sender;
	
	@ManyToOne
	@JoinColumn(name="receiver_id")
	private Account receiver;
	
	
	private LocalDateTime date_time;
	
	public LocalDateTime getDate_time() {
		return date_time;
	}

	public void setDate_time(LocalDateTime date_time) {
		this.date_time = date_time;
	}

	private Double amount;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

	public Account getReceiver() {
		return receiver;
	}

	public void setReceiver(Account receiver) {
		this.receiver = receiver;
	}
	
	@Override
	public String toString() {
		return "--ID--" + this.id + "------" + this.date_time + "-SENDER-" + this.sender.getId() + "-RECEIVER-" + this.receiver.getId() + "-AMOUNT-" + this.amount;
	}
}
