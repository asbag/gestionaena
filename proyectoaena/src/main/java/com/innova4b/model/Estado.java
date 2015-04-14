package com.innova4b.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Estado {

    /**
     */
    @NotNull
    @Column(unique = true)
    private Long idEstado;

    /**
     */
    private String nombre;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Avion> aviones = new HashSet<Avion>();
}
