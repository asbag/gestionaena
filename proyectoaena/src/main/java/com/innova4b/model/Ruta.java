package com.innova4b.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Ruta {

    /**
     */
    @NotNull
    @Column(unique = true)
    private int idRuta;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Avion> aviones = new HashSet<Avion>();

    /**
     */
    @ManyToOne
    private Aeropuerto aeropuertoOrigen;

    /**
     */
    @ManyToOne
    private Aeropuerto aeropuertoDestino;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Compania> rutas = new HashSet<Compania>();
}
