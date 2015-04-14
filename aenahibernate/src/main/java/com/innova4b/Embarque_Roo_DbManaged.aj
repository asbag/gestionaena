// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.innova4b;

import com.innova4b.Aeropuerto;
import com.innova4b.Embarque;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

privileged aspect Embarque_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "aeropuerto_idAeropuerto", referencedColumnName = "idAeropuerto", nullable = false, insertable = false, updatable = false)
    private Aeropuerto Embarque.aeropuertoIdAeropuerto;
    
    @Column(name = "num")
    @NotNull
    private Integer Embarque.num;
    
    public Aeropuerto Embarque.getAeropuertoIdAeropuerto() {
        return aeropuertoIdAeropuerto;
    }
    
    public void Embarque.setAeropuertoIdAeropuerto(Aeropuerto aeropuertoIdAeropuerto) {
        this.aeropuertoIdAeropuerto = aeropuertoIdAeropuerto;
    }
    
    public Integer Embarque.getNum() {
        return num;
    }
    
    public void Embarque.setNum(Integer num) {
        this.num = num;
    }
    
}
