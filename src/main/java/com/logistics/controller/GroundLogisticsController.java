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
import com.logistics.model.GroundLogisticsResponse;
import com.logistics.repository.CustomerRepository;
import com.logistics.repository.GroundLogisticsRepository;
import com.logistics.util.EnvioDescuento;

@RestController
@RequestMapping("/api/v1/")
//@CrossOrigin(origins = "http://localhost:4500")
//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class GroundLogisticsController {

	@Autowired
	GroundLogisticsRepository glogisticsRepository;
	
	@Autowired
	CustomerRepository customerRepository;	
	
	EnvioDescuento envioDescuento;
	
	public GroundLogisticsController(GroundLogisticsRepository glogisticsRepository, EnvioDescuento envioDescuento) {
		this.glogisticsRepository = glogisticsRepository;
		this.envioDescuento = envioDescuento;
	}
		
	
	// This method is used to get a list of all Ground Logistics
	@GetMapping("/groundlogistics")
	public List<GroundLogistics> listAllGroundLogistics() {
		return glogisticsRepository.findAll();
	}	
	
	
	
	@PostMapping(value="/groundlogistics")
	public ResponseEntity<GroundLogistics> guardarEnvio(@Valid @RequestBody GroundLogistics glogistics){
		System.out.println(glogistics.getNumeroGuia());
		System.out.println(glogistics.getDescuento());
		System.out.println(glogistics.getPrecioEnvio());

		Optional<Customer> customerOptional = customerRepository.findById(glogistics.getCustomer().getCustomer_id());
		
		if(!customerOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
				
		glogistics.setCustomer(customerOptional.get());
		
		float descuento = envioDescuento.calcularDescuento(glogistics.getPrecioEnvio(), glogistics.getCantidadProducto());			
		glogistics.setDescuento(descuento);
		
		GroundLogistics envioGuardado = glogisticsRepository.save(glogistics);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(envioGuardado.getId()).toUri();
		
		return ResponseEntity.created(ubicacion).body(envioGuardado);
	}	
	
	
	@GetMapping("/groundlogistics/{id}")
	//@GetMapping("/{id}")
	public ResponseEntity<GroundLogisticsResponse> obtenerEnvioPorId(@PathVariable Integer id){
		
		GroundLogisticsResponse glResponse = new GroundLogisticsResponse();
		Optional<GroundLogistics> envioOptional = glogisticsRepository.findById(id);
		
		if(!envioOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		System.out.println("El Cliente es: ");
		System.out.println(envioOptional.get().getCustomer().getCustomer_id());
		
		glResponse.setId(envioOptional.get().getId());
		glResponse.setCodigo_bodegaEntrega(envioOptional.get().getCodigo_bodegaEntrega());
		glResponse.setTipoProducto(envioOptional.get().getTipoProducto());
		glResponse.setCantidadProducto(envioOptional.get().getCantidadProducto());
		glResponse.setPrecioEnvio(envioOptional.get().getPrecioEnvio());
		glResponse.setDescuento(envioOptional.get().getDescuento());
		glResponse.setPlacaVehiculo(envioOptional.get().getPlacaVehiculo());
		glResponse.setNumeroGuia(envioOptional.get().getNumeroGuia());
		glResponse.setFechaRegistro(envioOptional.get().getFechaRegistro());
		glResponse.setFechaEntrega(envioOptional.get().getFechaEntrega());
		glResponse.setId(envioOptional.get().getId());		
		glResponse.setCustomer_id(envioOptional.get().getCustomer().getCustomer_id());
		
		return ResponseEntity.ok(glResponse);
	}	
	
	
	//este metodo sirve para eliminar un envio
	@DeleteMapping("/groundlogistics/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarEnvio(@PathVariable Integer id){
		GroundLogistics envioTerrestre = glogisticsRepository.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe el Envio Terrestre con el ID : " + id));
		
		glogisticsRepository.delete(envioTerrestre);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
	
	@PutMapping("/groundlogistics/{id}")
	public ResponseEntity<GroundLogistics> updateCustomer(@PathVariable Integer id, @RequestBody GroundLogistics envioDetails){
		GroundLogistics envioTerrestre = glogisticsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el  Envio Terrestre con el ID: " + id));
		
		envioTerrestre.setCodigo_bodegaEntrega(envioDetails.getCodigo_bodegaEntrega());
		envioTerrestre.setTipoProducto(envioDetails.getTipoProducto());
		envioTerrestre.setCantidadProducto(envioDetails.getCantidadProducto());
		envioTerrestre.setPrecioEnvio(envioDetails.getPrecioEnvio());
		envioTerrestre.setDescuento(envioDetails.getDescuento());
		envioTerrestre.setPlacaVehiculo(envioDetails.getPlacaVehiculo());
		envioTerrestre.setNumeroGuia(envioDetails.getNumeroGuia());
		
		GroundLogistics updatedEnvio = glogisticsRepository.save(envioTerrestre);
		return ResponseEntity.ok(updatedEnvio);				
	}	
	
}
