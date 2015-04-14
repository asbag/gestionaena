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

import com.innova4b.model.Ruta;
import com.innova4b.model.Aeropuerto;

/**
 * Backing bean for Ruta entities.
 * <p>
 * This class provides CRUD functionality for all Ruta entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class RutaBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Ruta entities
    */

   private Long id;

   public Long getId()
   {
      return this.id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   private Ruta ruta;

   public Ruta getRuta()
   {
      return this.ruta;
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
         this.ruta = this.example;
      }
      else
      {
         this.ruta = findById(getId());
      }
   }

   public Ruta findById(Long id)
   {

      return this.entityManager.find(Ruta.class, id);
   }

   /*
    * Support updating and deleting Ruta entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.ruta);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.ruta);
            return "view?faces-redirect=true&id=" + this.ruta.getId();
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
         Ruta deletableEntity = findById(getId());
         Aeropuerto aeropuertoByAeropuertoIdAeropuerto = deletableEntity.getAeropuertoByAeropuertoIdAeropuerto();
         aeropuertoByAeropuertoIdAeropuerto.getRutasForAeropuertoIdAeropuerto().remove(deletableEntity);
         deletableEntity.setAeropuertoByAeropuertoIdAeropuerto(null);
         this.entityManager.merge(aeropuertoByAeropuertoIdAeropuerto);
         Aeropuerto aeropuertoByAeropuertoIdAeropuerto1 = deletableEntity.getAeropuertoByAeropuertoIdAeropuerto1();
         aeropuertoByAeropuertoIdAeropuerto1.getRutasForAeropuertoIdAeropuerto1().remove(deletableEntity);
         deletableEntity.setAeropuertoByAeropuertoIdAeropuerto1(null);
         this.entityManager.merge(aeropuertoByAeropuertoIdAeropuerto1);
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
    * Support searching Ruta entities with pagination
    */

   private int page;
   private long count;
   private List<Ruta> pageItems;

   private Ruta example = new Ruta();

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

   public Ruta getExample()
   {
      return this.example;
   }

   public void setExample(Ruta example)
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
      Root<Ruta> root = countCriteria.from(Ruta.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<Ruta> criteria = builder.createQuery(Ruta.class);
      root = criteria.from(Ruta.class);
      TypedQuery<Ruta> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<Ruta> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      Aeropuerto aeropuertoByAeropuertoIdAeropuerto = this.example.getAeropuertoByAeropuertoIdAeropuerto();
      if (aeropuertoByAeropuertoIdAeropuerto != null)
      {
         predicatesList.add(builder.equal(root.get("aeropuertoByAeropuertoIdAeropuerto"), aeropuertoByAeropuertoIdAeropuerto));
      }
      Aeropuerto aeropuertoByAeropuertoIdAeropuerto1 = this.example.getAeropuertoByAeropuertoIdAeropuerto1();
      if (aeropuertoByAeropuertoIdAeropuerto1 != null)
      {
         predicatesList.add(builder.equal(root.get("aeropuertoByAeropuertoIdAeropuerto1"), aeropuertoByAeropuertoIdAeropuerto1));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<Ruta> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Ruta entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Ruta> getAll()
   {

      CriteriaQuery<Ruta> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(Ruta.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(Ruta.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final RutaBean ejbProxy = this.sessionContext.getBusinessObject(RutaBean.class);

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context,
               UIComponent component, String value)
         {

            return ejbProxy.findById(Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context,
               UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((Ruta) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Ruta add = new Ruta();

   public Ruta getAdd()
   {
      return this.add;
   }

   public Ruta getAdded()
   {
      Ruta added = this.add;
      this.add = new Ruta();
      return added;
   }
}