// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.innova4b.model;

import com.innova4b.model.Pasajero;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Pasajero_Roo_Jpa_Entity {
    
    declare @type: Pasajero: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Pasajero.id;
    
    @Version
    @Column(name = "version")
    private Integer Pasajero.version;
    
    public Long Pasajero.getId() {
        return this.id;
    }
    
    public void Pasajero.setId(Long id) {
        this.id = id;
    }
    
    public Integer Pasajero.getVersion() {
        return this.version;
    }
    
    public void Pasajero.setVersion(Integer version) {
        this.version = version;
    }
    
}
