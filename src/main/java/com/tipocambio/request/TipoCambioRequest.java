package com.tipocambio.request;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class TipoCambioRequest {
	
	private long id;
	private double monto;
	private double tipo_cambio;
	private String moneda_origen;
	private String moneda_destino;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public double getTipo_cambio() {
		return tipo_cambio;
	}
	public void setTipo_cambio(double tipo_cambio) {
		this.tipo_cambio = tipo_cambio;
	}
	public String getMoneda_origen() {
		return moneda_origen;
	}
	public void setMoneda_origen(String moneda_origen) {
		this.moneda_origen = moneda_origen;
	}
	public String getMoneda_destino() {
		return moneda_destino;
	}
	public void setMoneda_destino(String moneda_destino) {
		this.moneda_destino = moneda_destino;
	}
	
}
