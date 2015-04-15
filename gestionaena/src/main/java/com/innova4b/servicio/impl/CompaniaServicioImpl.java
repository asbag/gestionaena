package com.innova4b.servicio.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.innova4b.modelo.Compania;
import com.innova4b.servicio.CompaniaServicio;
import com.innova4b.sessionfactory.HibernateUtil;

public class CompaniaServicioImpl implements CompaniaServicio {
	@Override
	public List<String> listarCompanias() {
		Query query = null;
		Session session = null;
		List<String> list = null;

		try{
			String hql = "select C.nombre from Compania C";
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			query = session.createQuery(hql);
			list = query.list();
			transaction.commit();
		} catch (HibernateException e){
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Compania findByName(String nombre) {
		Query query = null;
		Session session = null;
		List<Compania> list = null;
		Compania compania = null;
		
		try{
			String hql = "from Compania C where C.nombre = :nombre";
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			query = session.createQuery(hql).setString("nombre", nombre);
			list = query.list();
			transaction.commit();
		} catch (HibernateException e){
			e.printStackTrace();
		}

		return compania;
	}
}
