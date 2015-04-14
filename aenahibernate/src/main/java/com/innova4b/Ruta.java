package com.innova4b;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(identifierType = RutaPK.class, versionField = "", table = "ruta")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "companias", "avions", "aeropuertoIdAeropuerto1", "aeropuertoIdAeropuerto" })
public class Ruta {
}
