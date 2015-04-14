package com.innova4b;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "billete")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "avionIdAvion", "pasajeroIdPasajero" })
public class Billete {
}