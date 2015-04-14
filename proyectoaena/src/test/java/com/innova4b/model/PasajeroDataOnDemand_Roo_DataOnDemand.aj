// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.innova4b.model;

import com.innova4b.model.Pasajero;
import com.innova4b.model.PasajeroDataOnDemand;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect PasajeroDataOnDemand_Roo_DataOnDemand {
    
    declare @type: PasajeroDataOnDemand: @Component;
    
    private Random PasajeroDataOnDemand.rnd = new SecureRandom();
    
    private List<Pasajero> PasajeroDataOnDemand.data;
    
    public Pasajero PasajeroDataOnDemand.getNewTransientPasajero(int index) {
        Pasajero obj = new Pasajero();
        setApellidos(obj, index);
        setFechaNacimiento(obj, index);
        setIdPasajero(obj, index);
        setNombre(obj, index);
        return obj;
    }
    
    public void PasajeroDataOnDemand.setApellidos(Pasajero obj, int index) {
        String apellidos = "apellidos_" + index;
        obj.setApellidos(apellidos);
    }
    
    public void PasajeroDataOnDemand.setFechaNacimiento(Pasajero obj, int index) {
        Date fechaNacimiento = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setFechaNacimiento(fechaNacimiento);
    }
    
    public void PasajeroDataOnDemand.setIdPasajero(Pasajero obj, int index) {
        Long idPasajero = new Integer(index).longValue();
        obj.setIdPasajero(idPasajero);
    }
    
    public void PasajeroDataOnDemand.setNombre(Pasajero obj, int index) {
        String nombre = "nombre_" + index;
        obj.setNombre(nombre);
    }
    
    public Pasajero PasajeroDataOnDemand.getSpecificPasajero(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Pasajero obj = data.get(index);
        Long id = obj.getId();
        return Pasajero.findPasajero(id);
    }
    
    public Pasajero PasajeroDataOnDemand.getRandomPasajero() {
        init();
        Pasajero obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Pasajero.findPasajero(id);
    }
    
    public boolean PasajeroDataOnDemand.modifyPasajero(Pasajero obj) {
        return false;
    }
    
    public void PasajeroDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = Pasajero.findPasajeroEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Pasajero' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Pasajero>();
        for (int i = 0; i < 10; i++) {
            Pasajero obj = getNewTransientPasajero(i);
            try {
                obj.persist();
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}