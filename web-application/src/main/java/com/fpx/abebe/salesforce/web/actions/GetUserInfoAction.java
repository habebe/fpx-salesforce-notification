package com.fpx.abebe.salesforce.web.actions;

import org.hibernate.Session;

import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.database.DataAccess;
import com.fpx.abebe.salesforce.model.User;
import com.fpx.abebe.salesforce.web.application.Application;
import com.opensymphony.xwork2.ActionSupport;

public class GetUserInfoAction extends AbstractAction 
{
	private static final long serialVersionUID = 1L;
	private String name = null;
	private String id = null;
	
	public String execute() 
	{
		Access access = this.getAccess();
		if(access != null)
		{
			Application application = this.getApplication();
			DataAccess dataAccess = application.getDataAccess();
			Session session = dataAccess.getSessionFactory().openSession();
			session.beginTransaction();
			User user = dataAccess.queryUser(access.getUserId(),session);
			if(user != null)
			{
				this.setName(user.getName());
			}
			this.setId(access.getUserId());
			session.getTransaction().commit();
			session.close();
		}
		return ActionSupport.SUCCESS;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GetUserInfoAction [name=" + name + ", id=" + id + "]";
	}


}