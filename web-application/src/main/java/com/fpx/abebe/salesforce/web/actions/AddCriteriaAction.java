package com.fpx.abebe.salesforce.web.actions;

import org.hibernate.Session;

import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.database.DataAccess;
import com.fpx.abebe.salesforce.model.NotificationCriteria;
import com.fpx.abebe.salesforce.model.User;
import com.fpx.abebe.salesforce.web.application.Application;
import com.opensymphony.xwork2.ActionSupport;

public class AddCriteriaAction extends AbstractAction 
{
	private static final long serialVersionUID = 1L;
	private String name;
	private String expression;
	private String ownerId;
	private boolean enabled;
	private NotificationCriteria result = null;
	
	public String add() 
	{
		Access access = this.getAccess();
		System.out.println("AddCriterionAction.add access:" + access);
		if(access != null)
		{
			System.out.println("Add Criteria user:"+access.getUserId()+" " +this.toString());
			Application application = this.getApplication();
			DataAccess dataAccess = application.getDataAccess();
			Session session = dataAccess.getSessionFactory().openSession();
			session.beginTransaction();
			User owner = dataAccess.queryUser(access.getUserId(),session);
			result = new NotificationCriteria(this.getName(),this.getExpression(),owner,this.isEnabled());
			session.saveOrUpdate(result);
			session.getTransaction().commit();
			session.close();
			return ActionSupport.SUCCESS;
		}
		return ActionSupport.ERROR;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	
	public NotificationCriteria getResult() {
		return result;
	}

	public void setResult(NotificationCriteria criteria) {
		this.result = criteria;
	}

	@Override
	public String toString() {
		return "AddCriterionAction [name=" + name + ", expression=" + expression + ", ownerId=" + ownerId + ", enabled="
				+ enabled + "]";
	}
}