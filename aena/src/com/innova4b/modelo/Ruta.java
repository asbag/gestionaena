package com.innova4b.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ruta")
public class Ruta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name="idRuta")
	private Long id = null;

	@ManyToMany(mappedBy="rutas")
    private Set<Avion> aviones = new HashSet<Avion>();
	
	@ManyToOne
	@JoinColumn(name="aeropuerto_idAeropuerto")
	private Aeropuerto aeropuertoOrigen;

	@ManyToOne
	@JoinColumn(name="aeropuerto_idAeropuerto1", insertable = false, updatable = false)
	private Aeropuerto aeropuertoDestino;

	public Set<Avion> getAviones() {
		return aviones;
	}

	public void setAviones(Set<Avion> aviones) {
		this.aviones = aviones;
	}

	public Aeropuerto getAeropuertoOrigen() {
		return aeropuertoOrigen;
	}

	public void setAeropuertoOrigen(Aeropuerto aeropuertoOrigen) {
		this.aeropuertoOrigen = aeropuertoOrigen;
	}

	public Aeropuerto getAeropuertoDestino() {
		return aeropuertoDestino;
	}

	public void setAeropuertoDestino(Aeropuerto aeropuertoDestino) {
		this.aeropuertoDestino = aeropuertoDestino;
	}

	public Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	

	
	
}
