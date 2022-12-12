package com.challengebp.dto;

import java.math.BigDecimal;

import com.challengebp.model.object.AccountType;


/**
 * @author David Mogrovejo
 */
public class AccountDTO {
	private String numeroCuenta;
	private AccountType tipo;
	private BigDecimal saldoInicial;
	private String  cliente;
	private boolean estado =  true;
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public AccountType getTipo() {
		return tipo;
	}
	public void setTipo(AccountType tipo) {
		this.tipo = tipo;
	}
	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}
	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
