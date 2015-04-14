package com.innova4b;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(identifierType = EmbarquePK.class, versionField = "", table = "embarque")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "aeropuertoIdAeropuerto" })
public class Embarque {
}
