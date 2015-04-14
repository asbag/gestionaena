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
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Billete {

    /**
     */
    @NotNull
    @Column(unique = true)
    private Long idBillete;

    /**
     */
    private String codgo;

    /**
     */
    private String asiento;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date fecha;

    /**
     */
    private String estado;

    /**
     */
    @ManyToOne
    private Avion avion;

    /**
     */
    @ManyToOne
    private Pasajero pasajero;
}
