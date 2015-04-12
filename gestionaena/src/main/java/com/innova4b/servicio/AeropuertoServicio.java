package com.innova4b.servicio;

import java.util.List;

import com.innova4b.modelo.Aeropuerto;

public interface AeropuertoServicio {
	public int numPuertasDisponibles ();
	
	public List<String> obtenerAeropuertos();
	
	public Aeropuerto findByName(String name);
}