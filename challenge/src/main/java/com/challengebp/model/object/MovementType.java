package com.challengebp.model.object;

public enum MovementType {

	CREDITO(1), DEBITO(2);
	private Integer id;
	private MovementType(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
