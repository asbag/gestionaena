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

import com.innova4b.model.Pasajero;
import com.innova4b.model.Billete;
import java.util.Iterator;

/**
 * Backing bean for Pasajero entities.
 * <p>
 * This class provides CRUD functionality for all Pasajero entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class PasajeroBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Pasajero entities
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

   private Pasajero pasajero;

   public Pasajero getPasajero()
   {
      return this.pasajero;
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
         this.pasajero = this.example;
      }
      else
      {
         this.pasajero = findById(getId());
      }
   }

   public Pasajero findById(Integer id)
   {

      return this.entityManager.find(Pasajero.class, id);
   }

   /*
    * Support updating and deleting Pasajero entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.pasajero);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.pasajero);
            return "view?faces-redirect=true&id=" + this.pasajero.getIdPasajero();
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
         Pasajero deletableEntity = findById(getId());
         Iterator<Billete> iterBilletes = deletableEntity.getBilletes().iterator();
         for (; iterBilletes.hasNext();)
         {
            Billete nextInBilletes = iterBilletes.next();
            nextInBilletes.setPasajero(null);
            iterBilletes.remove();
            this.entityManager.merge(nextInBilletes);
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
    * Support searching Pasajero entities with pagination
    */

   private int page;
   private long count;
   private List<Pasajero> pageItems;

   private Pasajero example = new Pasajero();

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

   public Pasajero getExample()
   {
      return this.example;
   }

   public void setExample(Pasajero example)
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
      Root<Pasajero> root = countCriteria.from(Pasajero.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<Pasajero> criteria = builder.createQuery(Pasajero.class);
      root = criteria.from(Pasajero.class);
      TypedQuery<Pasajero> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<Pasajero> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      int idPasajero = this.example.getIdPasajero();
      if (idPasajero != 0)
      {
         predicatesList.add(builder.equal(root.get("idPasajero"), idPasajero));
      }
      String nombre = this.example.getNombre();
      if (nombre != null && !"".equals(nombre))
      {
         predicatesList.add(builder.like(root.<String> get("nombre"), '%' + nombre + '%'));
      }
      String apellidos = this.example.getApellidos();
      if (apellidos != null && !"".equals(apellidos))
      {
         predicatesList.add(builder.like(root.<String> get("apellidos"), '%' + apellidos + '%'));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<Pasajero> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Pasajero entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Pasajero> getAll()
   {

      CriteriaQuery<Pasajero> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(Pasajero.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(Pasajero.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final PasajeroBean ejbProxy = this.sessionContext.getBusinessObject(PasajeroBean.class);

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

            return String.valueOf(((Pasajero) value).getIdPasajero());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Pasajero add = new Pasajero();

   public Pasajero getAdd()
   {
      return this.add;
   }

   public Pasajero getAdded()
   {
      Pasajero added = this.add;
      this.add = new Pasajero();
      return added;
   }
}