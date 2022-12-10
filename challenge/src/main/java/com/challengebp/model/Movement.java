package com.challengebp.model;

import java.math.BigDecimal;
import java.util.Date;

import com.challengebp.model.object.MovementType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
/**
 * 
 * Entity that maps the movements table, which is used to manage the movements of the accounts
 * @author David Mogrovejo
 */
@Entity
public class Movement {
	private Long id;
	private Date date;
	private MovementType movementType;
	private BigDecimal value =  BigDecimal.ZERO;
	private BigDecimal balance =  BigDecimal.ZERO;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MOVEMENT_ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	
}
