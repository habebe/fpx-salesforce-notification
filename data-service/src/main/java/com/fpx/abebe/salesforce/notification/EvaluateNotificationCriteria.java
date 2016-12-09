package com.fpx.abebe.salesforce.notification;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.fpx.abebe.salesforce.database.DataAccess;
import com.fpx.abebe.salesforce.model.Opportunity;
import com.fpx.abebe.salesforce.model.User;

public class EvaluateNotificationCriteria implements Command
{
	private CLI cli;
	public EvaluateNotificationCriteria(CLI cli)
	{
		this.setCli(cli);
	}

	public boolean execute() 
	{
		boolean status = false;
		DataAccess dataAccess = this.getCli().getDataAccess();
		Session session = dataAccess.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<Opportunity> query = session.createQuery("from Opportunity",Opportunity.class); 
		List<Opportunity> list = query.getResultList();
		for(Opportunity o:list)
		{
			System.out.println("===================================");
			System.out.println("Op:" + o);
			System.out.println("-----------------------------------");
			User owner = session.get(User.class, o.getOwnerId());
			System.out.println("Owner:" + owner);
			Date createdDate = owner.getCreatedDate();
			System.out.println("===================================" + createdDate);
			
			
			
			
		}
		session.getTransaction().commit();

		return status;
	}

	public CLI getCli() {
		return cli;
	}

	public void setCli(CLI cli) {
		this.cli = cli;
	}
}
