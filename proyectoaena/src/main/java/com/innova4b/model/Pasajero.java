package com.innova4b.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Pasajero {

    /**
     */
    @NotNull
    @Column(unique = true)
    private Long idPasajero;

    /**
     */
    private String nombre;

    /**
     */
    private String apellidos;

    /**
     */
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date fechaNacimiento;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Billete> billetes = new HashSet<Billete>();
}
