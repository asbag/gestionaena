package com.innova4b.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.innova4b.modelo.Aeropuerto;
import com.innova4b.servicio.AeropuertoServicio;
import com.innova4b.sessionfactory.HibernateUtil;

public class AeropuertoServicioImpl implements AeropuertoServicio {

	@Override
	public int numPuertasDisponibles() {
		Aeropuerto aerop = new Aeropuerto();
		
		return aerop.getNumPuertas();
	}

	@Override
	public List<String> obtenerAeropuertos() {
		List<String> list = null;

		Query query = null;
		Session session = null;
		try {
			String hql = "select A.nombre from Aeropuerto A";
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			query = session.createQuery(hql);
			list = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Exception in HqlQuery");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Aeropuerto findByName(String name) {
		Session session = null;
		Criteria criteria = session.createCriteria(Aeropuerto.class).add(Restrictions.eq("nombre", name));
		return (Aeropuerto)criteria.list();
	}

}
