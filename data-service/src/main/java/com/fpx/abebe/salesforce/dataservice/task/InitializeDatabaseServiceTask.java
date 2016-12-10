package com.fpx.abebe.salesforce.dataservice.task;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.fpx.abebe.salesforce.database.DataAccess;
import com.fpx.abebe.salesforce.dataservice.DataService;
import com.fpx.abebe.salesforce.model.Opportunity;
import com.fpx.abebe.salesforce.model.SalesForceObject;
import com.fpx.abebe.salesforce.model.User;
import com.fpx.abebe.salesforce.query.OpportunityQueryResult;
import com.fpx.abebe.salesforce.query.QueryResult;
import com.fpx.abebe.salesforce.query.QueryService;
import com.fpx.abebe.salesforce.query.UserQueryResult;

public class InitializeDatabaseServiceTask extends AbstractServiceTask
{
	public InitializeDatabaseServiceTask(DataService dataService) 
	{
		super(dataService);
	}

	public boolean execute() 
	{
		boolean status = false;
		this.getLogger().info("Running " + this.getClass());
		if(this.getAccess() != null)
		{

			try {
				QueryService queryService = new QueryService(
						getDataService().getClientConnection(),
						this.getAccess());
				QueryResult<?> result;
				this.getLogger().debug("Query " + User.class.getSimpleName());
				result = queryService.queryAll(UserQueryResult.class);
				for(SalesForceObject i : result.getRecords())
				{
					this.getLogger().debug("sobject:" + i);
				}
				this.getLogger().info("Persisting "+ User.class.getSimpleName() +".");
				DataAccess dataAccess = this.getDataAccess();
				dataAccess.persist(result);
				this.getLogger().debug("Query " + Opportunity.class.getSimpleName());
				result = queryService.queryAll(OpportunityQueryResult.class);
				for(SalesForceObject i : result.getRecords())
				{
					this.getLogger().debug("sobject:" + i);
				}
				this.getLogger().info("Persisting "+ Opportunity.class.getSimpleName() +".");
				dataAccess.persist(result);
				status = true;
			} 
			catch (ClientProtocolException e) 
			{
				this.getLogger().error("Protocol error ",e);
				e.printStackTrace();
			} 
			catch (InstantiationException e) 
			{
				this.getLogger().error("Instance creation error ",e);
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				this.getLogger().error("Access error ",e);
				e.printStackTrace();
			}
			catch (IOException e) 
			{
				this.getLogger().error("I/O error ",e);
				e.printStackTrace();
			}
		}
		else
		{
			this.getLogger().error("Must have access token to run this command " + this.getClass().getSimpleName());
		}
		return status;
	}
}
