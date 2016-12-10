package com.fpx.abebe.salesforce.dataservice;
/*package com.fpx.abebe.salesforce.notification;

import java.util.List;

import org.hibernate.Session;

import com.fpx.abebe.salesforce.database.DataAccess;
import com.fpx.abebe.salesforce.dataservices.CLI;
import com.fpx.abebe.salesforce.model.NotificationCriteria;
import com.fpx.abebe.salesforce.model.User;

public class AddNotificationCriteria implements Command
{
	private CLI cli;
	public AddNotificationCriteria(CLI cli)
	{
		this.setCli(cli);
	}

	public boolean execute() 
	{
		boolean status = false;
		DataAccess dataAccess = this.getCli().getDataAccess();
		Session session = dataAccess.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<User> users = dataAccess.queryUsers(session);
		System.out.println("Number of users = " + users.size());
		System.out.println("--------------------------------------------------------------");
		System.out.printf("%20s%20s%20s\n","ID","First name","Last name");
		System.out.println("--------------------------------------------------------------");
		
		for(User user:users)
		{
			System.out.printf("%20s%20s%20s\n",
					user.getId(),
					user.getFirstName(),
					user.getLastName());				
		}
		System.out.println("--------------------------------------------------------------");
		session.getTransaction().commit();
		
		
		boolean done = false;
		
		while(done == false)
		{
			String userId = System.console().readLine("[%s]","UserId (Empty to quit)");
			if(userId.trim().length() == 0)
			{
				done = true;
				getCli().logger.info("Done.");
			}
			else
			{
				String expression = System.console().readLine("[%s]","Expression");
				expression = expression.trim();
				if(expression.length() > 0)
				{
					User user = dataAccess.queryUser(userId);
					if(user == null)
					{
						getCli().logger.error("Unable to find user with Id '" + userId + "'");
					}
					else
					{
						NotificationCriteria criteria = new NotificationCriteria();
						criteria.setExpression(expression);
						criteria.setOwnerId(user.getId());
						dataAccess.save(criteria);
					}
				}
				else
				{
					getCli().logger.error("Invalid expression.");
				}
			}
			
		}
		return status;
	}

	public CLI getCli() {
		return cli;
	}

	public void setCli(CLI cli) {
		this.cli = cli;
	}
}
*/