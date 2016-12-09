package com.fpx.abebe.salesforce.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.fpx.abebe.salesforce.model.SalesForceObject;
import com.fpx.abebe.salesforce.query.QueryResult;

public class DataAccess 
{
	private SessionFactory sessionFactory = null;
	public DataAccess()
	{
	}

	public synchronized SessionFactory getSessionFactory()
	{
		if(sessionFactory == null)
		{
			Configuration configuration;
			configuration = new Configuration();
			sessionFactory = configuration.configure().buildSessionFactory();
		}
		return sessionFactory;
	}

	public synchronized void closeSession()
	{
		if(this.sessionFactory != null)
			this.sessionFactory.close();
	}

	public void persist(QueryResult<?> result)
	{
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		for(SalesForceObject object:result.getRecords())
		{
			session.saveOrUpdate(object);
		}
		session.getTransaction().commit();
		session.close();
	}
}