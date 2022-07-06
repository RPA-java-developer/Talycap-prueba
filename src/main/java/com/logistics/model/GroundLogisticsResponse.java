package com.logistics.model;

import java.util.Date;

public class GroundLogisticsResponse {
	
	private Integer id;	       
    private String codigo_bodegaEntrega;  
    private String tipoProducto;
    private Integer cantidadProducto;   
    private float precioEnvio;
    private float descuento;
    private String placaVehiculo;
    private String numeroGuia;
    private Date fechaRegistro;
    private Date fechaEntrega;
	private int customer_id;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigo_bodegaEntrega() {
		return codigo_bodegaEntrega;
	}
	public void setCodigo_bodegaEntrega(String codigo_bodegaEntrega) {
		this.codigo_bodegaEntrega = codigo_bodegaEntrega;
	}
	public String getTipoProducto() {
		return tipoProducto;
	}
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	public Integer getCantidadProducto() {
		return cantidadProducto;
	}
	public void setCantidadProducto(Integer cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}
	public float getPrecioEnvio() {
		return precioEnvio;
	}
	public void setPrecioEnvio(float precioEnvio) {
		this.precioEnvio = precioEnvio;
	}
	public float getDescuento() {
		return descuento;
	}
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	public String getPlacaVehiculo() {
		return placaVehiculo;
	}
	public void setPlacaVehiculo(String placaVehiculo) {
		this.placaVehiculo = placaVehiculo;
	}
	public String getNumeroGuia() {
		return numeroGuia;
	}
	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
    	
}
