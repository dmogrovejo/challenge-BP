package com.challengebp.model;

import java.math.BigDecimal;
import java.util.Date;

import com.challengebp.model.object.MovementType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
/**
 * 
 * Entity that maps the movements table, which is used to manage the movements of the accounts
 * @author David Mogrovejo
 */
@Entity
public class Movement {
	private String id;
	private Date dateMovement;
	private MovementType movementType;
	private BigDecimal value =  BigDecimal.ZERO;
	private BigDecimal balance =  BigDecimal.ZERO;
	private Account account;
	private String description;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "MOVEMENT_ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_MOVEMENT")
	public Date getDateMovement() {
		return dateMovement;
	}
	public void setDateMovement(Date date) {
		this.dateMovement = date;
	}
	@Column(name = "MOVEMENT_TYPE")
	@Enumerated(EnumType.STRING)
	public MovementType getMovementType() {
		return movementType;
	}
	public void setMovementType(MovementType movementType) {
		this.movementType = movementType;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	@ManyToOne
	@JoinColumn(name = "ACCOUNT_ID")
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
