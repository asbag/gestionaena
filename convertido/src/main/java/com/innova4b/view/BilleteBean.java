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

import com.innova4b.model.Billete;
import com.innova4b.model.Avion;
import com.innova4b.model.Pasajero;

/**
 * Backing bean for Billete entities.
 * <p>
 * This class provides CRUD functionality for all Billete entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class BilleteBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Billete entities
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

   private Billete billete;

   public Billete getBillete()
   {
      return this.billete;
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
         this.billete = this.example;
      }
      else
      {
         this.billete = findById(getId());
      }
   }

   public Billete findById(Integer id)
   {

      return this.entityManager.find(Billete.class, id);
   }

   /*
    * Support updating and deleting Billete entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.billete);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.billete);
            return "view?faces-redirect=true&id=" + this.billete.getIdBillete();
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
         Billete deletableEntity = findById(getId());
         Avion avion = deletableEntity.getAvion();
         avion.getBilletes().remove(deletableEntity);
         deletableEntity.setAvion(null);
         this.entityManager.merge(avion);
         Pasajero pasajero = deletableEntity.getPasajero();
         pasajero.getBilletes().remove(deletableEntity);
         deletableEntity.setPasajero(null);
         this.entityManager.merge(pasajero);
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
    * Support searching Billete entities with pagination
    */

   private int page;
   private long count;
   private List<Billete> pageItems;

   private Billete example = new Billete();

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

   public Billete getExample()
   {
      return this.example;
   }

   public void setExample(Billete example)
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
      Root<Billete> root = countCriteria.from(Billete.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<Billete> criteria = builder.createQuery(Billete.class);
      root = criteria.from(Billete.class);
      TypedQuery<Billete> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<Billete> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      int idBillete = this.example.getIdBillete();
      if (idBillete != 0)
      {
         predicatesList.add(builder.equal(root.get("idBillete"), idBillete));
      }
      Avion avion = this.example.getAvion();
      if (avion != null)
      {
         predicatesList.add(builder.equal(root.get("avion"), avion));
      }
      Pasajero pasajero = this.example.getPasajero();
      if (pasajero != null)
      {
         predicatesList.add(builder.equal(root.get("pasajero"), pasajero));
      }
      String codgo = this.example.getCodgo();
      if (codgo != null && !"".equals(codgo))
      {
         predicatesList.add(builder.like(root.<String> get("codgo"), '%' + codgo + '%'));
      }
      String asiento = this.example.getAsiento();
      if (asiento != null && !"".equals(asiento))
      {
         predicatesList.add(builder.like(root.<String> get("asiento"), '%' + asiento + '%'));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<Billete> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Billete entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Billete> getAll()
   {

      CriteriaQuery<Billete> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(Billete.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(Billete.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final BilleteBean ejbProxy = this.sessionContext.getBusinessObject(BilleteBean.class);

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

            return String.valueOf(((Billete) value).getIdBillete());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Billete add = new Billete();

   public Billete getAdd()
   {
      return this.add;
   }

   public Billete getAdded()
   {
      Billete added = this.add;
      this.add = new Billete();
      return added;
   }
}