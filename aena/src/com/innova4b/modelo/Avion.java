package com.innova4b.modelo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name = "avion")
public class Avion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name="idAvion")
	private Integer id = null;

	@Column(name="modelo")
	private String modelo;

	@Column(name="max_pasajeros")
	private int maxPasajeros;

	@Column(name="hora_salida")
	private Timestamp horaSalida;

	@Column(name="hora_llegada")
	private Timestamp horaLlegada;

	@Column(name="codigo_licencia")
	private int codigoLicencia;

	@Column(name="estado_licencia")
	private boolean estadoLicencia;

	@Column(name="caducidad_licencia")
	private Date caducidadLicencia;

	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="ruta_has_avion", 
                joinColumns={@JoinColumn(name="ruta_idRuta")}, 
                inverseJoinColumns={@JoinColumn(name="avion_idAvion")})
	private Set<Ruta> rutas = new HashSet<Ruta>();
	
	
	public Integer getId() {
		return id;
	}

	protected void setId(Integer id) {
		this.id = id;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public int getMaxPasajeros() {
		return maxPasajeros;
	}


	public void setMaxPasajeros(int maxPasajeros) {
		this.maxPasajeros = maxPasajeros;
	}


	public Timestamp getHoraSalida() {
		return horaSalida;
	}


	public void setHoraSalida(Timestamp horaSalida) {
		this.horaSalida = horaSalida;	
	}


	public Timestamp getHoraLlegada() {
		return horaLlegada;
	}


	public void setHoraLlegada(Timestamp horaLlegada) {
		this.horaLlegada = horaLlegada;
	}


	public int getCodigoLicencia() {
		return codigoLicencia;
	}


	public void setCodigoLicencia(int codigoLicencia) {
		this.codigoLicencia = codigoLicencia;
	}


	public boolean isEstadoLicencia() {
		return estadoLicencia;
	}


	public void setEstadoLicencia(boolean estadoLicencia) {
		this.estadoLicencia = estadoLicencia;
	}


	public Date getCaducidadLicencia() {
		return caducidadLicencia;
	}


	public void setCaducidadLicencia(Date caducidadLicencia) {
		this.caducidadLicencia = caducidadLicencia;
	}

	public Set<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(Set<Ruta> rutas) {
		this.rutas = rutas;
	}


}