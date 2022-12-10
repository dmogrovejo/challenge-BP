package com.challengebp.model;

import java.math.BigDecimal;

import com.challengebp.model.object.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entity that maps the Account table, which is used to manage the Accounts
 * @author David Mogrovejo
 */
@Entity
public class Account {
	private Long id;
	private String accountNumber;
	private AccountType accountType;
	private BigDecimal inicialBalnce;
	private boolean status =  true;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_ID")	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "ACCOUNT_NUMBER")
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "ACCOUNT_TYPE")
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	@Column(name = "INICIAL_BALANCE")
	public BigDecimal getInicialBalnce() {
		return inicialBalnce;
	}
	public void setInicialBalnce(BigDecimal inicialBalnce) {
		this.inicialBalnce = inicialBalnce;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
