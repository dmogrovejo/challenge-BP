package com.challengebp.model;

import java.math.BigDecimal;



import com.challengebp.model.object.AccountType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

/**
 * Entity that maps the Account table, which is used to manage the Accounts
 * @author David Mogrovejo
 */
@Entity
public class Account {
	private Long id;
	private String accountNumber;
	private AccountType accountType;
	private BigDecimal inicialBalance;
	private Client client;
	private boolean status =  true;
	
	
	
	@Id
    @Column(name = "ACCOUNT_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ACCOUNT_ID")
    @TableGenerator(name = "ACCOUNT_ID", table = "ID_GEN",
            pkColumnName = "NAME_PK", valueColumnName = "VALUE_PK",
            pkColumnValue = "ACCOUNT_ID", allocationSize = 1, initialValue = 1)	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "ACCOUNT_NUMBER", unique = true, nullable = false)
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
	public BigDecimal getInicialBalance() {
		return inicialBalance;
	}
	public void setInicialBalance(BigDecimal inicialBalance) {
		this.inicialBalance = inicialBalance;
	}
	
	
	
	@JoinColumn(name = "CLIENT_ID")
	@ManyToOne
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", accountNumber=" + accountNumber + ", accountType=" + accountType+"]";
	}
	
	
}
