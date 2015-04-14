package com.innova4b.model;
// Generated 12-abr-2015 23:05:10 by Hibernate Tools 3.4.0.CR1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Estadoembarque generated by hbm2java
 */
@Entity
@Table(name="estadoembarque"
    ,catalog="aena"
)
public class Estadoembarque  implements java.io.Serializable {


     private int idEstadoEmb;
     private String nombre;

    public Estadoembarque() {
    }

    public Estadoembarque(int idEstadoEmb, String nombre) {
       this.idEstadoEmb = idEstadoEmb;
       this.nombre = nombre;
    }
   
     @Id 

    
    @Column(name="idEstadoEmb", unique=true, nullable=false)
    public int getIdEstadoEmb() {
        return this.idEstadoEmb;
    }
    
    public void setIdEstadoEmb(int idEstadoEmb) {
        this.idEstadoEmb = idEstadoEmb;
    }

    
    @Column(name="nombre", nullable=false, length=45)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }




}


