package com.fpx.abebe.salesforce.database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.fpx.abebe.salesforce.model.Opportunity;
import com.fpx.abebe.salesforce.model.User;

public class OpportunityCollection 
{
	private List<Opportunity> result = new ArrayList<Opportunity>(); 
	public void collect(Session session,String userId)
	{
		this.getResult().clear();
		User user = session.get(User.class, userId);
		this.collectInternal(session, user);
	}
	
	private void collectInternal(Session session,User user)
	{
		this.collectInternalForUser(session, user);
		List<User> employees = this.queryEmployees(user, session);
		if(employees != null)
		{
			for(User employee:employees)
			{
				this.collectInternal(session, employee);
			}
		}
	}

	private void collectInternalForUser(Session session,User user)
	{
		if(user != null)
		{
			Query<Opportunity> query = session.createQuery(
					"from Opportunity where ownerId=:ownerId and isClosed=:isClosed",
					Opportunity.class); 
			query.setParameter("ownerId",user.getId());
			query.setParameter("isClosed",false);
			List<Opportunity> queryResult = query.getResultList();
			this.addToCollection(queryResult);		
		}
	}
	
	private List<User> queryEmployees(User user,Session session)
	{
		if(user != null)
		{
			Query<User> query = session.createQuery(
					"from User where managerId=:managerId",
					User.class); 
			query.setParameter("managerId",user.getId());
			List<User> queryResult = query.getResultList();
			return queryResult;
		}
		return null;
	}
	
	private void addToCollection(List<Opportunity> queryResult) 
	{
		if(queryResult != null)
		{
			this.getResult().addAll(queryResult);
		}
	}

	public List<Opportunity> getResult() 
	{
		return result;
	}
}
