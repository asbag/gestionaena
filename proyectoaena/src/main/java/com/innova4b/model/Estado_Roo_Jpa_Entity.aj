// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.innova4b.model;

import com.innova4b.model.Estado;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Estado_Roo_Jpa_Entity {
    
    declare @type: Estado: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Estado.id;
    
    @Version
    @Column(name = "version")
    private Integer Estado.version;
    
    public Long Estado.getId() {
        return this.id;
    }
    
    public void Estado.setId(Long id) {
        this.id = id;
    }
    
    public Integer Estado.getVersion() {
        return this.version;
    }
    
    public void Estado.setVersion(Integer version) {
        this.version = version;
    }
    
}