// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.innova4b;

import com.innova4b.Billete;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect Billete_Roo_Jpa_Entity {
    
    declare @type: Billete: @Entity;
    
    declare @type: Billete: @Table(name = "billete");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idBillete")
    private Integer Billete.idBillete;
    
    public Integer Billete.getIdBillete() {
        return this.idBillete;
    }
    
    public void Billete.setIdBillete(Integer id) {
        this.idBillete = id;
    }
    
}
