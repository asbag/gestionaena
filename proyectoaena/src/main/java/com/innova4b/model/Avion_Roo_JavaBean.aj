// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.innova4b.model;

import com.innova4b.model.Avion;
import com.innova4b.model.Ruta;
import java.util.Date;
import java.util.Set;

privileged aspect Avion_Roo_JavaBean {
    
    public Long Avion.getIdAvion() {
        return this.idAvion;
    }
    
    public void Avion.setIdAvion(Long idAvion) {
        this.idAvion = idAvion;
    }
    
    public String Avion.getModelo() {
        return this.modelo;
    }
    
    public void Avion.setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public int Avion.getMaxPasajeros() {
        return this.maxPasajeros;
    }
    
    public void Avion.setMaxPasajeros(int maxPasajeros) {
        this.maxPasajeros = maxPasajeros;
    }
    
    public Date Avion.getHoraSalida() {
        return this.horaSalida;
    }
    
    public void Avion.setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }
    
    public Date Avion.getHoraLlegada() {
        return this.horaLlegada;
    }
    
    public void Avion.setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }
    
    public String Avion.getCodigoLicencia() {
        return this.codigoLicencia;
    }
    
    public void Avion.setCodigoLicencia(String codigoLicencia) {
        this.codigoLicencia = codigoLicencia;
    }
    
    public int Avion.getEstadoLicencia() {
        return this.estadoLicencia;
    }
    
    public void Avion.setEstadoLicencia(int estadoLicencia) {
        this.estadoLicencia = estadoLicencia;
    }
    
    public Date Avion.getCaducidadLicencia() {
        return this.caducidadLicencia;
    }
    
    public void Avion.setCaducidadLicencia(Date caducidadLicencia) {
        this.caducidadLicencia = caducidadLicencia;
    }
    
    public Set<Ruta> Avion.getRutas() {
        return this.rutas;
    }
    
    public void Avion.setRutas(Set<Ruta> rutas) {
        this.rutas = rutas;
    }
    
}