package com.logistics.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;



@Entity
@Table(name = "ground_logistics")
public class GroundLogistics {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")	
	private Integer id;	
       
    @Column(name = "codigo_bodegaEntrega")
    private String codigo_bodegaEntrega;
    
    @Column(name = "tipoProducto")
    private String tipoProducto;
    
    @Column(name = "cantidadProducto")
    private Integer cantidadProducto;
    
    @Column(name = "precioEnvio")
    private float precioEnvio;
    
    @Column(name = "descuento")
    private float descuento;
    
    @Column(name = "placaVehiculo")
    private String placaVehiculo;
    
    @Column(name = "numeroGuia")
    private String numeroGuia;
    
    @Column(name = "fechaRegistro", nullable = true)
    private Date fechaRegistro;
    
    @Column(name = "fechaEntrega", nullable = true)
    private Date fechaEntrega;

	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Customer customer;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getCodigo_bodegaEntrega() {
		return codigo_bodegaEntrega;
	}
	public void setCodigo_bodegaEntrega(String codigo_bodegaEntrega) {
		this.codigo_bodegaEntrega = codigo_bodegaEntrega;
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
