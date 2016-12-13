package com.fpx.abebe.salesforce.web.actions;

import java.util.List;

import org.hibernate.Session;

import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.database.DataAccess;
import com.fpx.abebe.salesforce.database.NotificationCriteriaCollection;
import com.fpx.abebe.salesforce.model.NotificationCriteria;
import com.fpx.abebe.salesforce.model.User;
import com.fpx.abebe.salesforce.web.application.Application;
import com.opensymphony.xwork2.ActionSupport;

public class GetCriteriaAction extends AbstractAction 
{
	private static final long serialVersionUID = 1L;
	private List<NotificationCriteria> result = null;
	
	public String get() 
	{
		Access access = this.getAccess();
		System.out.println("GetCriteriaAction.access = " + access);
		if(access != null)
		{
			Application application = this.getApplication();
			DataAccess dataAccess = application.getDataAccess();
			Session session = dataAccess.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			User user = dataAccess.queryUser(access.getUserId(),session);
			if(user != null)
			{
				NotificationCriteriaCollection collection = dataAccess.queryNotificationCriteriaForUser(user, session);
				this.setResult(collection.getResult());
				System.out.println("Result:" + this.getResult());
			}
			session.getTransaction().commit();
			session.close();
		}
		return ActionSupport.SUCCESS;
	}

	public List<NotificationCriteria> getResult() {
		return result;
	}

	public void setResult(List<NotificationCriteria> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "AddCriterionAction [result=" + result + "]";
	}
}