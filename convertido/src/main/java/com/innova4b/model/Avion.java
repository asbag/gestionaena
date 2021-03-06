package com.innova4b.model;
// Generated 12-abr-2015 23:05:10 by Hibernate Tools 3.4.0.CR1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Avion generated by hbm2java
 */
@Entity
@Table(name="avion"
    ,catalog="aena"
    , uniqueConstraints = @UniqueConstraint(columnNames="modelo") 
)
public class Avion  implements java.io.Serializable {


     private int idAvion;
     private Estado estado;
     private Compania compania;
     private String modelo;
     private Integer maxPasajeros;
     private Date horaSalida;
     private Date horaLlegada;
     private String codigoLicencia;
     private Byte estadoLicencia;
     private Date caducidadLicencia;
     private Set<Ruta> rutas = new HashSet<Ruta>(0);
     private Set<Billete> billetes = new HashSet<Billete>(0);

    public Avion() {
    }

	
    public Avion(int idAvion, Estado estado, Compania compania, String modelo) {
        this.idAvion = idAvion;
        this.estado = estado;
        this.compania = compania;
        this.modelo = modelo;
    }
    public Avion(int idAvion, Estado estado, Compania compania, String modelo, Integer maxPasajeros, Date horaSalida, Date horaLlegada, String codigoLicencia, Byte estadoLicencia, Date caducidadLicencia, Set<Ruta> rutas, Set<Billete> billetes) {
       this.idAvion = idAvion;
       this.estado = estado;
       this.compania = compania;
       this.modelo = modelo;
       this.maxPasajeros = maxPasajeros;
       this.horaSalida = horaSalida;
       this.horaLlegada = horaLlegada;
       this.codigoLicencia = codigoLicencia;
       this.estadoLicencia = estadoLicencia;
       this.caducidadLicencia = caducidadLicencia;
       this.rutas = rutas;
       this.billetes = billetes;
    }
   
     @Id 

    
    @Column(name="idAvion", unique=true, nullable=false)
    public int getIdAvion() {
        return this.idAvion;
    }
    
    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="estado_idestado", nullable=false)
    public Estado getEstado() {
        return this.estado;
    }
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="compania_idCompania", nullable=false)
    public Compania getCompania() {
        return this.compania;
    }
    
    public void setCompania(Compania compania) {
        this.compania = compania;
    }

    
    @Column(name="modelo", unique=true, nullable=false, length=45)
    public String getModelo() {
        return this.modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    
    @Column(name="max_pasajeros")
    public Integer getMaxPasajeros() {
        return this.maxPasajeros;
    }
    
    public void setMaxPasajeros(Integer maxPasajeros) {
        this.maxPasajeros = maxPasajeros;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="hora_salida", length=19)
    public Date getHoraSalida() {
        return this.horaSalida;
    }
    
    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="hora_llegada", length=19)
    public Date getHoraLlegada() {
        return this.horaLlegada;
    }
    
    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    
    @Column(name="codigo_licencia", length=45)
    public String getCodigoLicencia() {
        return this.codigoLicencia;
    }
    
    public void setCodigoLicencia(String codigoLicencia) {
        this.codigoLicencia = codigoLicencia;
    }

    
    @Column(name="estado_licencia")
    public Byte getEstadoLicencia() {
        return this.estadoLicencia;
    }
    
    public void setEstadoLicencia(Byte estadoLicencia) {
        this.estadoLicencia = estadoLicencia;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="caducidad_licencia", length=10)
    public Date getCaducidadLicencia() {
        return this.caducidadLicencia;
    }
    
    public void setCaducidadLicencia(Date caducidadLicencia) {
        this.caducidadLicencia = caducidadLicencia;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="ruta_has_avion", catalog="aena", joinColumns = { 
        @JoinColumn(name="avion_idAvion", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="ruta_idRuta", nullable=false, updatable=false) })
    public Set<Ruta> getRutas() {
        return this.rutas;
    }
    
    public void setRutas(Set<Ruta> rutas) {
        this.rutas = rutas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="avion")
    public Set<Billete> getBilletes() {
        return this.billetes;
    }
    
    public void setBilletes(Set<Billete> billetes) {
        this.billetes = billetes;
    }




}


