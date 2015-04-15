package com.innova4b.modelo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="estadoembarque")
public class EstadoEmbarque implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@Column(name="idEstadoEmb")
	private Long id = null;
	
	@Column(name="nombre")
	private String nombre;

	@OneToMany(mappedBy="estadoEmbarque")
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

	public Set<PuertaEmbarque> getEmbarques() {
		return embarques;
	}

	public void setEmbarques(Set<PuertaEmbarque> embarques) {
		this.embarques = embarques;
	}
	
}
