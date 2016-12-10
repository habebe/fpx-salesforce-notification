package com.fpx.abebe.salesforce.database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.fpx.abebe.salesforce.model.NotificationCriteria;
import com.fpx.abebe.salesforce.model.User;

public class NotificationCriteriaCollection 
{
	private List<NotificationCriteria> result = new ArrayList<NotificationCriteria>(); 
	public void collect(Session session,User user)
	{
		this.getResult().clear();
		this.collectInternal(session, user);
	}

	private void collectInternal(Session session,User user)
	{
		if(user != null)
		{
			String managerId = user.getManagerId();
			if(managerId != null)
			{
				User manager = session.get(User.class, managerId);
				this.collectInternal(session, manager);
			}
			Query<NotificationCriteria> query = session.createQuery(
					"from NotificationCriteria where ownerId =:ownerId",
					NotificationCriteria.class); 
			query.setParameter("ownerId", user.getId());
			List<NotificationCriteria> queryResult = query.getResultList();
			this.addToCollection(queryResult);		
		}
	}
	
	private void addToCollection(List<NotificationCriteria> queryResult) 
	{
		if(queryResult != null)
		{
			this.getResult().addAll(queryResult);
		}
	}

	public List<NotificationCriteria> getResult() 
	{
		return result;
	}
}
