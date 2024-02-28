package com.tipocambio.bean;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TIPOCAMBIO")
@Getter
@Setter
@AllArgsConstructor
public class TipoCambio {
	
	@Id
	@Column
	private int id;
	private double monto;
	private double tipo_cambio;
	private String moneda_origen;
	private String moneda_destino;
	private double monto_tipo_cambio;
	private String usuario_creacion;
	private String fecha_creacion;
	private String usuario_modificacion;
	private String fecha_modificacion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public double getMonto_tipo_cambio() {
		return monto_tipo_cambio;
	}
	public void setMonto_tipo_cambio(double monto_tipo_cambio) {
		this.monto_tipo_cambio = monto_tipo_cambio;
	}
	public String getUsuario_creacion() {
		return usuario_creacion;
	}
	public void setUsuario_creacion(String usuario_creacion) {
		this.usuario_creacion = usuario_creacion;
	}
	
	public String getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public String getUsuario_modificacion() {
		return usuario_modificacion;
	}
	public void setUsuario_modificacion(String usuario_modificacion) {
		this.usuario_modificacion = usuario_modificacion;
	}
	public String getFecha_modificacion() {
		return fecha_modificacion;
	}
	public void setFecha_modificacion(String fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}
	
	
}
