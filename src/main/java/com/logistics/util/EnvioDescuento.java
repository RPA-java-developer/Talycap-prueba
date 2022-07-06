package com.logistics.util;

import org.springframework.stereotype.Component;

@Component
public class EnvioDescuento {

	public float calcularDescuento(float precioEnvio, Integer cantidadProducto) {
		float descuento = 0; 
		
		if (cantidadProducto > 10) {
			descuento = (float) (precioEnvio * 0.05);
		} else {
			descuento = 0;
		}
		return descuento;
	}
	
}
