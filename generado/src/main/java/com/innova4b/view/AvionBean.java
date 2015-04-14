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

import com.innova4b.model.Avion;
import com.innova4b.model.Compania;

/**
 * Backing bean for Avion entities.
 * <p>
 * This class provides CRUD functionality for all Avion entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class AvionBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Avion entities
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

   private Avion avion;

   public Avion getAvion()
   {
      return this.avion;
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
         this.avion = this.example;
      }
      else
      {
         this.avion = findById(getId());
      }
   }

   public Avion findById(Integer id)
   {

      return this.entityManager.find(Avion.class, id);
   }

   /*
    * Support updating and deleting Avion entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.avion);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.avion);
            return "view?faces-redirect=true&id=" + this.avion.getIdAvion();
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
         Avion deletableEntity = findById(getId());
         Compania compania = deletableEntity.getCompania();
         compania.getAvions().remove(deletableEntity);
         deletableEntity.setCompania(null);
         this.entityManager.merge(compania);
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
    * Support searching Avion entities with pagination
    */

   private int page;
   private long count;
   private List<Avion> pageItems;

   private Avion example = new Avion();

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

   public Avion getExample()
   {
      return this.example;
   }

   public void setExample(Avion example)
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
      Root<Avion> root = countCriteria.from(Avion.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<Avion> criteria = builder.createQuery(Avion.class);
      root = criteria.from(Avion.class);
      TypedQuery<Avion> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<Avion> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      int idAvion = this.example.getIdAvion();
      if (idAvion != 0)
      {
         predicatesList.add(builder.equal(root.get("idAvion"), idAvion));
      }
      Compania compania = this.example.getCompania();
      if (compania != null)
      {
         predicatesList.add(builder.equal(root.get("compania"), compania));
      }
      String modelo = this.example.getModelo();
      if (modelo != null && !"".equals(modelo))
      {
         predicatesList.add(builder.like(root.<String> get("modelo"), '%' + modelo + '%'));
      }
      Integer maxPasajeros = this.example.getMaxPasajeros();
      if (maxPasajeros != null && maxPasajeros.intValue() != 0)
      {
         predicatesList.add(builder.equal(root.get("maxPasajeros"), maxPasajeros));
      }
      String codigoLicencia = this.example.getCodigoLicencia();
      if (codigoLicencia != null && !"".equals(codigoLicencia))
      {
         predicatesList.add(builder.like(root.<String> get("codigoLicencia"), '%' + codigoLicencia + '%'));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<Avion> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Avion entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Avion> getAll()
   {

      CriteriaQuery<Avion> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(Avion.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(Avion.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final AvionBean ejbProxy = this.sessionContext.getBusinessObject(AvionBean.class);

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

            return String.valueOf(((Avion) value).getIdAvion());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Avion add = new Avion();

   public Avion getAdd()
   {
      return this.add;
   }

   public Avion getAdded()
   {
      Avion added = this.add;
      this.add = new Avion();
      return added;
   }
}