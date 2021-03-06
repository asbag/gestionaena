// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.innova4b.model;

import com.innova4b.model.Aeropuerto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Aeropuerto_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Aeropuerto.entityManager;
    
    public static final List<String> Aeropuerto.fieldNames4OrderClauseFilter = java.util.Arrays.asList("idAeropuerto", "nombre", "numPuertas", "pais", "rutasOrigen", "rutasDestino", "embarques");
    
    public static final EntityManager Aeropuerto.entityManager() {
        EntityManager em = new Aeropuerto().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Aeropuerto.countAeropuertoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Aeropuerto o", Long.class).getSingleResult();
    }
    
    public static List<Aeropuerto> Aeropuerto.findAllAeropuertoes() {
        return entityManager().createQuery("SELECT o FROM Aeropuerto o", Aeropuerto.class).getResultList();
    }
    
    public static List<Aeropuerto> Aeropuerto.findAllAeropuertoes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Aeropuerto o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Aeropuerto.class).getResultList();
    }
    
    public static Aeropuerto Aeropuerto.findAeropuerto(Long id) {
        if (id == null) return null;
        return entityManager().find(Aeropuerto.class, id);
    }
    
    public static List<Aeropuerto> Aeropuerto.findAeropuertoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Aeropuerto o", Aeropuerto.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Aeropuerto> Aeropuerto.findAeropuertoEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Aeropuerto o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Aeropuerto.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Aeropuerto.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Aeropuerto.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Aeropuerto attached = Aeropuerto.findAeropuerto(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Aeropuerto.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Aeropuerto.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Aeropuerto Aeropuerto.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Aeropuerto merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
