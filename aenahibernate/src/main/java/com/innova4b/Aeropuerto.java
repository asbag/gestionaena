package com.innova4b;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "aeropuerto")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "embarques", "rutas", "rutas1" })
public class Aeropuerto {
}
