package com.fpx.abebe.salesforce.web.actions;

import org.hibernate.Session;

import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.database.DataAccess;
import com.fpx.abebe.salesforce.model.NotificationCriteria;
import com.fpx.abebe.salesforce.web.application.Application;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateCriteriaAction extends AbstractAction 
{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String expression;
	private boolean enabled;
	private String ownerId;
	private NotificationCriteria result = null;
	
	public String update() 
	{
		Access access = this.getAccess();
		System.out.println("UpdateCriterionAction.update access:" + access);
		if(access != null)
		{
			System.out.println("Update Criteria user:"+access.getUserId()+" " +this.toString());
			Application application = this.getApplication();
			DataAccess dataAccess = application.getDataAccess();
			Session session = dataAccess.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			NotificationCriteria criteria = session.get(NotificationCriteria.class,this.getId());
			criteria.setName(this.getName());
			criteria.setExpression(this.getExpression());
			criteria.setEnabled(this.isEnabled());
			session.update(criteria);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UpdateCriteriaAction [id=" + id + ", name=" + name + ", expression=" + expression + ", enabled="
				+ enabled + ", ownerId=" + ownerId + ", result=" + result + "]";
	}
}