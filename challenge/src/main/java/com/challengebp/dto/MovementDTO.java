package com.challengebp.dto;

import java.math.BigDecimal;

/**
 * @author David Mogrovejo
 */
public class MovementDTO {

	private BigDecimal valor =  BigDecimal.ZERO;
	private String numeroCuenta;
	private String descripcion;
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
