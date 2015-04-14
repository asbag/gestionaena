package com.innova4b.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.innova4b.model.Aeropuerto;
import com.innova4b.model.Ruta;
import java.util.Iterator;

/**
 * Backing bean for Aeropuerto entities.
 * <p>
 * This class provides CRUD functionality for all Aeropuerto entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class AeropuertoBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Aeropuerto entities
    */

   private Integer id;

   public Integer getId()
   {
      return this.id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   private Aeropuerto aeropuerto;

   public Aeropuerto getAeropuerto()
   {
      return this.aeropuerto;
   }

   @Inject
   private Conversation conversation;

   @PersistenceContext(type = PersistenceContextType.EXTENDED)
   private EntityManager entityManager;

   public String create()
   {

      this.conversation.begin();
      return "create?faces-redirect=true";
   }

   public void retrieve()
   {

      if (FacesContext.getCurrentInstance().isPostback())
      {
         return;
      }

      if (this.conversation.isTransient())
      {
         this.conversation.begin();
      }

      if (this.id == null)
      {
         this.aeropuerto = this.example;
      }
      else
      {
         this.aeropuerto = findById(getId());
      }
   }

   public Aeropuerto findById(Integer id)
   {

      return this.entityManager.find(Aeropuerto.class, id);
   }

   /*
    * Support updating and deleting Aeropuerto entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.aeropuerto);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.aeropuerto);
            return "view?faces-redirect=true&id=" + this.aeropuerto.getIdAeropuerto();
         }
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   public String delete()
   {
      this.conversation.end();

      try
      {
         Aeropuerto deletableEntity = findById(getId());
         Iterator<Ruta> iterRutasForAeropuertoIdAeropuerto1 = deletableEntity.getRutasForAeropuertoIdAeropuerto1().iterator();
         for (; iterRutasForAeropuertoIdAeropuerto1.hasNext();)
         {
            Ruta nextInRutasForAeropuertoIdAeropuerto1 = iterRutasForAeropuertoIdAeropuerto1.next();
            nextInRutasForAeropuertoIdAeropuerto1.setAeropuertoByAeropuertoIdAeropuerto1(null);
            iterRutasForAeropuertoIdAeropuerto1.remove();
            this.entityManager.merge(nextInRutasForAeropuertoIdAeropuerto1);
         }
         Iterator<Ruta> iterRutasForAeropuertoIdAeropuerto = deletableEntity.getRutasForAeropuertoIdAeropuerto().iterator();
         for (; iterRutasForAeropuertoIdAeropuerto.hasNext();)
         {
            Ruta nextInRutasForAeropuertoIdAeropuerto = iterRutasForAeropuertoIdAeropuerto.next();
            nextInRutasForAeropuertoIdAeropuerto.setAeropuertoByAeropuertoIdAeropuerto(null);
            iterRutasForAeropuertoIdAeropuerto.remove();
            this.entityManager.merge(nextInRutasForAeropuertoIdAeropuerto);
         }
         this.entityManager.remove(deletableEntity);
         this.entityManager.flush();
         return "search?faces-redirect=true";
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   /*
    * Support searching Aeropuerto entities with pagination
    */

   private int page;
   private long count;
   private List<Aeropuerto> pageItems;

   private Aeropuerto example = new Aeropuerto();

   public int getPage()
   {
      return this.page;
   }

   public void setPage(int page)
   {
      this.page = page;
   }

   public int getPageSize()
   {
      return 10;
   }

   public Aeropuerto getExample()
   {
      return this.example;
   }

   public void setExample(Aeropuerto example)
   {
      this.example = example;
   }

   public void search()
   {
      this.page = 0;
   }

   public void paginate()
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

      // Populate this.count

      CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
      Root<Aeropuerto> root = countCriteria.from(Aeropuerto.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<Aeropuerto> criteria = builder.createQuery(Aeropuerto.class);
      root = criteria.from(Aeropuerto.class);
      TypedQuery<Aeropuerto> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<Aeropuerto> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      int idAeropuerto = this.example.getIdAeropuerto();
      if (idAeropuerto != 0)
      {
         predicatesList.add(builder.equal(root.get("idAeropuerto"), idAeropuerto));
      }
      String nombre = this.example.getNombre();
      if (nombre != null && !"".equals(nombre))
      {
         predicatesList.add(builder.like(root.<String> get("nombre"), '%' + nombre + '%'));
      }
      Integer numPuertas = this.example.getNumPuertas();
      if (numPuertas != null && numPuertas.intValue() != 0)
      {
         predicatesList.add(builder.equal(root.get("numPuertas"), numPuertas));
      }
      String pais = this.example.getPais();
      if (pais != null && !"".equals(pais))
      {
         predicatesList.add(builder.like(root.<String> get("pais"), '%' + pais + '%'));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<Aeropuerto> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Aeropuerto entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Aeropuerto> getAll()
   {

      CriteriaQuery<Aeropuerto> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(Aeropuerto.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(Aeropuerto.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final AeropuertoBean ejbProxy = this.sessionContext.getBusinessObject(AeropuertoBean.class);

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context,
               UIComponent component, String value)
         {

            return ejbProxy.findById(Integer.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context,
               UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((Aeropuerto) value).getIdAeropuerto());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Aeropuerto add = new Aeropuerto();

   public Aeropuerto getAdd()
   {
      return this.add;
   }

   public Aeropuerto getAdded()
   {
      Aeropuerto added = this.add;
      this.add = new Aeropuerto();
      return added;
   }
}