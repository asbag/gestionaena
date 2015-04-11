package com.innova4b.sessionfactory;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static final SessionFactory sf = buildSessionFactory();
	private static SessionFactory buildSessionFactory() {//throws HibernateException {

		try{
			Configuration cfg = new Configuration().configure("/resources/hibernate.cfg.xml");
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();

			return cfg.buildSessionFactory(serviceRegistry);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		/*
		} catch (Throwable e) {
			throw new ExceptionInInitializerError();
		}
		*/
	}

	public static SessionFactory getSessionFactory() {
		return sf;
	}

}

