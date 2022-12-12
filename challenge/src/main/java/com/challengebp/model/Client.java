package com.challengebp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 * Entity that maps the Client table, which is used to manage clients
 * @author David Mogrovejo
 */

@Entity
public class Client extends Person{

	private Long id;
	private String password;
	private boolean status =  true;
	
	@Id
    @Column(name = "CLIENT_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CLIENT_ID")
    @TableGenerator(name = "CLIENT_ID", table = "ID_GEN",
            pkColumnName = "NAME_PK", valueColumnName = "VALUE_PK",
            pkColumnValue = "CLIENT_ID", allocationSize = 1, initialValue = 1)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
