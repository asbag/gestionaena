// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.innova4b;

import com.innova4b.Embarque;
import com.innova4b.model.Aeropuerto;

privileged aspect Embarque_Roo_JavaBean {
    
    public Long Embarque.getIdEmbarque() {
        return this.idEmbarque;
    }
    
    public void Embarque.setIdEmbarque(Long idEmbarque) {
        this.idEmbarque = idEmbarque;
    }
    
    public int Embarque.getNumero() {
        return this.numero;
    }
    
    public void Embarque.setNumero(int numero) {
        this.numero = numero;
    }
    
    public Aeropuerto Embarque.getAeropuerto() {
        return this.aeropuerto;
    }
    
    public void Embarque.setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
    
}