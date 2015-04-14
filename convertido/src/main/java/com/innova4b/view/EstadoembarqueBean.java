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

import com.innova4b.model.Estadoembarque;

/**
 * Backing bean for Estadoembarque entities.
 * <p>
 * This class provides CRUD functionality for all Estadoembarque entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class EstadoembarqueBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Estadoembarque entities
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

   private Estadoembarque estadoembarque;

   public Estadoembarque getEstadoembarque()
   {
      return this.estadoembarque;
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
         this.estadoembarque = this.example;
      }
      else
      {
         this.estadoembarque = findById(getId());
      }
   }

   public Estadoembarque findById(Integer id)
   {

      return this.entityManager.find(Estadoembarque.class, id);
   }

   /*
    * Support updating and deleting Estadoembarque entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.estadoembarque);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.estadoembarque);
            return "view?faces-redirect=true&id=" + this.estadoembarque.getIdEstadoEmb();
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
         Estadoembarque deletableEntity = findById(getId());

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
    * Support searching Estadoembarque entities with pagination
    */

   private int page;
   private long count;
   private List<Estadoembarque> pageItems;

   private Estadoembarque example = new Estadoembarque();

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

   public Estadoembarque getExample()
   {
      return this.example;
   }

   public void setExample(Estadoembarque example)
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
      Root<Estadoembarque> root = countCriteria.from(Estadoembarque.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<Estadoembarque> criteria = builder.createQuery(Estadoembarque.class);
      root = criteria.from(Estadoembarque.class);
      TypedQuery<Estadoembarque> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<Estadoembarque> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      int idEstadoEmb = this.example.getIdEstadoEmb();
      if (idEstadoEmb != 0)
      {
         predicatesList.add(builder.equal(root.get("idEstadoEmb"), idEstadoEmb));
      }
      String nombre = this.example.getNombre();
      if (nombre != null && !"".equals(nombre))
      {
         predicatesList.add(builder.like(root.<String> get("nombre"), '%' + nombre + '%'));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<Estadoembarque> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Estadoembarque entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Estadoembarque> getAll()
   {

      CriteriaQuery<Estadoembarque> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(Estadoembarque.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(Estadoembarque.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final EstadoembarqueBean ejbProxy = this.sessionContext.getBusinessObject(EstadoembarqueBean.class);

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

            return String.valueOf(((Estadoembarque) value).getIdEstadoEmb());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Estadoembarque add = new Estadoembarque();

   public Estadoembarque getAdd()
   {
      return this.add;
   }

   public Estadoembarque getAdded()
   {
      Estadoembarque added = this.add;
      this.add = new Estadoembarque();
      return added;
   }
}