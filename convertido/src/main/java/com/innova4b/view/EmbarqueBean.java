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

import com.innova4b.model.Embarque;
import com.innova4b.model.Aeropuerto;

/**
 * Backing bean for Embarque entities.
 * <p>
 * This class provides CRUD functionality for all Embarque entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class EmbarqueBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Embarque entities
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

   private Embarque embarque;

   public Embarque getEmbarque()
   {
      return this.embarque;
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
         this.embarque = this.example;
      }
      else
      {
         this.embarque = findById(getId());
      }
   }

   public Embarque findById(Long id)
   {

      return this.entityManager.find(Embarque.class, id);
   }

   /*
    * Support updating and deleting Embarque entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.embarque);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.embarque);
            return "view?faces-redirect=true&id=" + this.embarque.getId();
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
         Embarque deletableEntity = findById(getId());
         Aeropuerto aeropuerto = deletableEntity.getAeropuerto();
         aeropuerto.getEmbarques().remove(deletableEntity);
         deletableEntity.setAeropuerto(null);
         this.entityManager.merge(aeropuerto);
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
    * Support searching Embarque entities with pagination
    */

   private int page;
   private long count;
   private List<Embarque> pageItems;

   private Embarque example = new Embarque();

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

   public Embarque getExample()
   {
      return this.example;
   }

   public void setExample(Embarque example)
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
      Root<Embarque> root = countCriteria.from(Embarque.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<Embarque> criteria = builder.createQuery(Embarque.class);
      root = criteria.from(Embarque.class);
      TypedQuery<Embarque> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<Embarque> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      Aeropuerto aeropuerto = this.example.getAeropuerto();
      if (aeropuerto != null)
      {
         predicatesList.add(builder.equal(root.get("aeropuerto"), aeropuerto));
      }
      int num = this.example.getNum();
      if (num != 0)
      {
         predicatesList.add(builder.equal(root.get("num"), num));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<Embarque> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Embarque entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Embarque> getAll()
   {

      CriteriaQuery<Embarque> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(Embarque.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(Embarque.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final EmbarqueBean ejbProxy = this.sessionContext.getBusinessObject(EmbarqueBean.class);

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

            return String.valueOf(((Embarque) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Embarque add = new Embarque();

   public Embarque getAdd()
   {
      return this.add;
   }

   public Embarque getAdded()
   {
      Embarque added = this.add;
      this.add = new Embarque();
      return added;
   }
}