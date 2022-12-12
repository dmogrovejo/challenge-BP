package com.challengebp.dto;

import java.math.BigDecimal;

import com.challengebp.model.object.AccountType;
import com.challengebp.model.object.MovementType;

/**
 * @author David Mogrovejo
 */
public class ReportDTO {
	private String fecha;
	private String cliente;
	private String numeroCuenta;
	private AccountType tipoCuenta;
	private BigDecimal saldoInicial;
	private boolean estado;
	private MovementType tipoMovimiento;
	private BigDecimal movimiento;
	private BigDecimal saldoDisponible;
	
	
	
	
	public ReportDTO(String fecha, String cliente, String numeroCuenta, AccountType tipoCuenta, BigDecimal saldoInicial,
			boolean estado, MovementType tipoMovimiento, BigDecimal movimiento, BigDecimal saldoDisponible) {
		super();
		this.fecha = fecha;
		this.cliente = cliente;
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.saldoInicial = saldoInicial;
		this.estado = estado;
		this.tipoMovimiento = tipoMovimiento;
		this.movimiento = movimiento;
		this.saldoDisponible = saldoDisponible;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public AccountType getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(AccountType tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}
	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public MovementType getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(MovementType tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public BigDecimal getMovimiento() {
		return movimiento;
	}
	public void setMovimiento(BigDecimal movimiento) {
		this.movimiento = movimiento;
	}
	public BigDecimal getSaldoDisponible() {
		return saldoDisponible;
	}
	public void setSaldoDisponible(BigDecimal saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	
	
	
	
	
}
