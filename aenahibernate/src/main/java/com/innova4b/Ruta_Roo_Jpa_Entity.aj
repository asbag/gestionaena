// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.innova4b;

import com.innova4b.Ruta;
import com.innova4b.RutaPK;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

privileged aspect Ruta_Roo_Jpa_Entity {
    
    declare @type: Ruta: @Entity;
    
    declare @type: Ruta: @Table(name = "ruta");
    
    @EmbeddedId
    private RutaPK Ruta.id;
    
    public RutaPK Ruta.getId() {
        return this.id;
    }
    
    public void Ruta.setId(RutaPK id) {
        this.id = id;
    }
    
}
