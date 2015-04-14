package com.innova4b;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "avion")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "rutas", "billetes", "companiaIdCompania", "estadoIdestado" })
public class Avion {
}
