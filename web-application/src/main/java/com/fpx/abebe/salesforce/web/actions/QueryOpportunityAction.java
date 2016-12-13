package com.fpx.abebe.salesforce.web.actions;

import java.util.List;

import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.database.DataAccess;
import com.fpx.abebe.salesforce.model.Opportunity;
import com.fpx.abebe.salesforce.web.application.Application;
import com.opensymphony.xwork2.ActionSupport;

public class QueryOpportunityAction extends AbstractAction 
{
	private static final long serialVersionUID = 1L;
	private List<Opportunity> result = null;

	public String execute() 
	{
		Access access = this.getAccess();
		if(access != null)
		{
			Application application = this.getApplication();
			DataAccess dataAccess = application.getDataAccess();
			this.result = dataAccess.queryOpportunityForUser(access.getUserId());
		}
		return ActionSupport.SUCCESS;
	}

	public List<Opportunity> getResult() {
		return result;
	}

	public void setResult(List<Opportunity> result) {
		this.result = result;
	}
}
