package com.innova4b;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.innova4b.model.Aeropuerto;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Embarque {

    /**
     */
    @NotNull
    @Column(unique = true)
    private Long idEmbarque;

    /**
     */
    private int numero;

    /**
     */
    @ManyToOne
    private Aeropuerto aeropuerto;
}
