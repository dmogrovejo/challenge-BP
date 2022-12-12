package com.challengebp.model.object;

/**
 * @author David Mogrovejo
 */
public enum AccountType {

	AHORROS(1), CORRIENTE(2);
	private Integer id;
	private AccountType(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
