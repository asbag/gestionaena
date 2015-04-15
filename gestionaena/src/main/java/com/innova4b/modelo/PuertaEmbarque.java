package com.innova4b.modelo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="embarque")
public class PuertaEmbarque implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name="idEmbarque")
	private Long id = null;

	@Column(name="num")
	private String num;

	@ManyToOne
	@JoinColumn(name="aeropuerto_idAeropuerto")
	private Aeropuerto aeropuerto;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "avion_idAvion")
	private Avion avion;

	@ManyToOne
	@JoinColumn(name="estadoembarque_idEstadoEmb")
	private EstadoEmbarque estadoEmbarque;
	
	public Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Aeropuerto getAeropuerto() {
		return aeropuerto;
	}

	public void setAeropuerto(Aeropuerto aeropuerto) {
		this.aeropuerto = aeropuerto;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public EstadoEmbarque getEstadoEmbarque() {
		return estadoEmbarque;
	}

	public void setEstadoEmbarque(EstadoEmbarque estadoEmbarque) {
		this.estadoEmbarque = estadoEmbarque;
	}

}
