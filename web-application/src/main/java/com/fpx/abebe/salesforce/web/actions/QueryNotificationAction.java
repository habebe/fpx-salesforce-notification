package com.fpx.abebe.salesforce.web.actions;

import java.util.List;

import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.database.DataAccess;
import com.fpx.abebe.salesforce.model.NotificationMessage;
import com.fpx.abebe.salesforce.web.application.Application;
import com.opensymphony.xwork2.ActionSupport;

public class QueryNotificationAction extends AbstractAction 
{
	private static final long serialVersionUID = 1L;
	private List<NotificationMessage> result = null;

	public String execute() 
	{
		Access access = this.getAccess();
		if(access != null)
		{
			Application application = this.getApplication();
			DataAccess dataAccess = application.getDataAccess();
			this.result = dataAccess.queryNotificationMessageForUser(access.getUserId());
		}
		return ActionSupport.SUCCESS;
	}

	public List<NotificationMessage> getResult() {
		return result;
	}

	public void setResult(List<NotificationMessage> result) {
		this.result = result;
	}
}
