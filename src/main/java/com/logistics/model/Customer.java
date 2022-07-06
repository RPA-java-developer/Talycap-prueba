package com.logistics.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	
	@Id
	private Integer customer_id;
	
	@Column(name = "name", length = 60, nullable = false)
	private String name;
	
	@Column(name = "email", length = 60, nullable = false, unique = true)
	private String email;
	
	@Column(name = "movil", length = 60, nullable = true)
	private String movil;
	
	@Column(name = "address", length = 60, nullable = true)
	private String address;
	
	@Column(name = "password", length = 10, nullable = false)
	private String password;
		
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<GroundLogistics> groundLogistics = new HashSet<>();	
	
	public Customer() {
	}
	
	public Customer(Integer customer_id, String name, String email, String movil, String address, String password) {
		super();
		this.customer_id = customer_id;
		this.name = name;
		this.email = email;
		this.movil = movil;
		this.address = address;
		this.password = password;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<GroundLogistics> getGroundLogistics() {
		return groundLogistics;
	}

	public void setGroundLogistics(Set<GroundLogistics> groundLogistics) {
		this.groundLogistics = groundLogistics;
	}	
	
}
