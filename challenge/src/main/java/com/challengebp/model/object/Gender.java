package com.challengebp.model.object;

public enum Gender {
	MASCULINO(1), FEMENINO(2), NO_DEFINIDO(3);
	private Integer id;
	private Gender(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
