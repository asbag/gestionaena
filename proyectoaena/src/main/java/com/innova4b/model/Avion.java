package com.innova4b.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Avion {

    /**
     */
    @NotNull
    @Column(unique = true)
    private Long idAvion;

    /**
     */
    @Size(max = 45)
    private String modelo;

    /**
     */
    @Column(name = "max_pasajeros")
    private int maxPasajeros;

    /**
     */
    @Column(name = "hora_salida")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date horaSalida;

    /**
     */
    @Column(name = "hora_llegada")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date horaLlegada;

    /**
     */
    @Column(name = "codigo_licencia")
    private String codigoLicencia;

    /**
     */
    @Column(name = "estado_licencia")
    private int estadoLicencia;

    /**
     */
    @Column(name = "caducidad_licencia")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date caducidadLicencia;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Ruta> rutas = new HashSet<Ruta>();
}
