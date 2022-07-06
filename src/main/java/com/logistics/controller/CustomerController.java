package com.logistics.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.logistics.exceptions.ResourceNotFoundException;
import com.logistics.model.Customer;
import com.logistics.model.GroundLogistics;
import com.logistics.repository.CustomerRepository;
import com.logistics.repository.GroundLogisticsRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4500")
public class CustomerController {

	@Autowired
	private CustomerRepository repository;
	
	// This method is used to get a list of all customers
	@GetMapping("/customers")
	public List<Customer> listAllCustomers() {
		return repository.findAll();
	}
	

	// This method is used to save a customer
	@PostMapping("/customers")
	public Customer saveCustomer(@RequestBody Customer customer) {
	//public String saveCustomer(@RequestBody Customer customer) {
		System.out.println(customer.getCustomer_id());
		customer.setPassword("123");
		return repository.save(customer);
		//return "se guarda customer";
	}
		
	
	// This method is used to search for a customer by Id
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomerByID(@PathVariable Integer id){
		Customer customer = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("The Customere with the ID "+id+" does not exist: "));
		return ResponseEntity.ok(customer);
	}
	

	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customerDetails){
		Customer customer = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID: " + id));
		customer.setName(customerDetails.getName());
		customer.setEmail(customerDetails.getEmail());
		customer.setMovil(customerDetails.getMovil());
		customer.setAddress(customerDetails.getAddress());		
		Customer updatedCustomer = repository.save(customer);
		return ResponseEntity.ok(updatedCustomer);				
	}
	
	 
	//este metodo sirve para eliminar un empleado
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarEmpleado(@PathVariable Integer id){
		Customer customer = repository.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID : " + id));
		
		repository.delete(customer);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }	
	
}
