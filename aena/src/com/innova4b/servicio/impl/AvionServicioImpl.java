package com.innova4b.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.innova4b.listas.ListaAviones;
import com.innova4b.listas.impl.ListaAvionesImpl;
import com.innova4b.modelo.Avion;
import com.innova4b.servicio.AvionServicio;
import com.innova4b.sessionfactory.HibernateUtil;

public class AvionServicioImpl implements AvionServicio {
	//Listar aviones con licencia caducada
	Session session;

	@Override
	public List<String> listarAvionesCaducados () {
		List<String> list = null;

		Query query = null;
		Session session = null;
		try {
			String hql = "select A.modelo from Avion A where A.estadoLicencia = 0";
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			query = session.createQuery(hql);
			list = query.list();

			query = session.createQuery(hql);
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Exception in HqlQuery");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<String> listarAvionesEspanioles() {
		Query query = null;
		Session session = null;
		//List<[]Objects> list = null;
		List<String> list = null;

		try{
			String hql = "select A.modelo from Avion A left join A.rutas R left join R.aeropuertoOrigen P where P.pais = :pais";
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			//query = session.createQuery(hql).setInteger("estado", 0);
			query = session.createQuery(hql).setString("pais", "ES");
			list = query.list();
		} catch (HibernateException e){
			System.out.println("Exception in HqlQuery Listar Aviones Españoles");
		}

		return list;
	}

}
