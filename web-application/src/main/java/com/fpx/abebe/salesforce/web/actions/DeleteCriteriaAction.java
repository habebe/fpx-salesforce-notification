package com.fpx.abebe.salesforce.web.actions;

import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.database.DataAccess;
import com.fpx.abebe.salesforce.web.application.Application;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteCriteriaAction extends AbstractAction 
{
	private static final long serialVersionUID = 1L;
	private int id;
	
	public String delete() 
	{
		Access access = this.getAccess();
		if(access != null)
		{
			Application application = this.getApplication();
			DataAccess dataAccess = application.getDataAccess();
			dataAccess.deleteNotificationCriteria(this.getId());
			return ActionSupport.SUCCESS;
		}
		return ActionSupport.ERROR;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DeleteCriteriaAction [id=" + id + "]";
	}


}