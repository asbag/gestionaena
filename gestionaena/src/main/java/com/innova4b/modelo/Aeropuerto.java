package com.innova4b.modelo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="aeropuerto")
public class Aeropuerto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idAeropuerto")
	private Long id = null;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="num_puertas")
	private int numPuertas;
	
	@Column(name="pais")
	private String pais;

	@OneToMany(mappedBy="aeropuertoOrigen")
	private Set<Ruta> rutaOrigen;

	@OneToMany(mappedBy="aeropuertoDestino")
	private Set<Ruta> rutaDestino;

	@OneToMany(mappedBy="aeropuerto")
	private Set<PuertaEmbarque> embarques;

	public Long getId() {
		return id;
	}
	protected void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumPuertas() {
		return numPuertas;
	}
	public void setNumPuertas(int numPuertas) {
		this.numPuertas = numPuertas;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public Set<Ruta> getRutaOrigen() {
		return rutaOrigen;
	}
	public void setRutaOrigen(Set<Ruta> rutaOrigen) {
		this.rutaOrigen = rutaOrigen;
	}

	public Set<Ruta> getRutaDestino() {
		return rutaDestino;
	}
	public void setRutaDestino(Set<Ruta> rutaDestino) {
		this.rutaDestino = rutaDestino;
	}
	public Set<PuertaEmbarque> getEmbarques() {
		return embarques;
	}
	public void setEmbarques(Set<PuertaEmbarque> embarques) {
		this.embarques = embarques;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aeropuerto other = (Aeropuerto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
