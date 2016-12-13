package com.fpx.abebe.salesforce.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.fpx.abebe.salesforce.model.NotificationCriteria;
import com.fpx.abebe.salesforce.model.NotificationMessage;
import com.fpx.abebe.salesforce.model.Opportunity;
import com.fpx.abebe.salesforce.model.SalesForceObject;
import com.fpx.abebe.salesforce.model.User;
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

	public synchronized void closeSessionFactory()
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

	public NotificationCriteriaCollection queryNotificationCriteriaForUser(User user)
	{
		NotificationCriteriaCollection collection;
		Session session = this.getSessionFactory().getCurrentSession();
		collection = this.queryNotificationCriteriaForUser(user,session);
		return collection;
	}

	public NotificationCriteriaCollection queryNotificationCriteriaForUser(User user,Session session)
	{
		NotificationCriteriaCollection collection;
		collection = new NotificationCriteriaCollection();
		collection.collect(session,user);
		return collection;
	}

	public List<Opportunity> queryOpportunityForUser(String userId)
	{
		Session session = this.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Opportunity> result = this.queryOpportunityForUser(userId, session);
		session.getTransaction().commit();
		return result;
	}

	public List<Opportunity> queryOpportunityForUser(User user)
	{
		Session session = this.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Opportunity> result = this.queryOpportunityForUser(user, session);
		session.getTransaction().commit();
		return result;
	}

	public List<Opportunity> queryOpportunityForUser(User user,Session session)
	{
		return queryOpportunityForUser(user.getId(),session);
	}	

	public List<Opportunity> queryOpportunityForUser(String userId,Session session)
	{
		Query<Opportunity> query = session.createQuery("from Opportunity where ownerId=:ownerId",Opportunity.class); 
		query.setParameter("ownerId",userId);
		return query.getResultList();
	}	

	public List<User> queryUsers(Session session)
	{
		Query<User> query = session.createQuery("from User",User.class); 
		List<User> result = query.getResultList();
		return result;
	}

	public List<User> queryUsers()
	{
		Session session = this.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<User> result = this.queryUsers(session);
		session.getTransaction().commit();
		return result;
	}

	public User queryUser(String userId, Session session) 
	{
		return session.get(User.class, userId);
	}

	public User queryUser(String userId) 
	{
		Session session = this.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User result = this.queryUser(userId, session);
		session.getTransaction().commit();
		return result;
	}

	public boolean save(NotificationCriteria criteria) 
	{
		Session session = this.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(criteria);
		session.getTransaction().commit();
		return true;
	}	

	public boolean deleteNotificationCriteria(int id)
	{
		Session session = this.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<?> q = session.createQuery("delete from NotificationCriteria where id = :id ");
		q.setParameter("id", id);
		q.executeUpdate();
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public List<NotificationMessage> queryNotificationMessageForUser(String userId) 
	{
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query<NotificationMessage> query = 
				session.createQuery("from NotificationMessage where userId=:userId",
						NotificationMessage.class); 
		query.setParameter("userId",userId);
		List<NotificationMessage> result = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return result;
	}
}