package com.innova4b.servicio;

import java.util.List;

import com.innova4b.modelo.Avion;

public interface AvionServicio {
	public List<String> listarAvionesCaducados();
	
	public List<String> listarAvionesEspanioles();
	
	public Integer numAsientosReservados(String avion);
	
	public Integer numAsientosOcupados(String avion);

	public List<String> listarAviones();
	
	public List<Avion> listarAvionesObjeto();
	
	public void insertarAvion(Avion avion);
	
	public Avion findById(String avionId);

}
