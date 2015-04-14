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
import com.innova4b.Embarque;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Aeropuerto {

    /**
     */
    @NotNull
    @Column(unique = true)
    private Long idAeropuerto;

    /**
     */
    private String nombre;

    /**
     */
    @Column(name = "num_puertas")
    private int numPuertas;

    /**
     */
    private String pais;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Ruta> rutasOrigen = new HashSet<Ruta>();

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Ruta> rutasDestino = new HashSet<Ruta>();

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Embarque> embarques = new HashSet<Embarque>();
}
