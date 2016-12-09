package com.fpx.abebe.salesforce.notification;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.fpx.abebe.salesforce.database.DataAccess;
import com.fpx.abebe.salesforce.model.Opportunity;
import com.fpx.abebe.salesforce.model.SalesForceObject;
import com.fpx.abebe.salesforce.model.User;
import com.fpx.abebe.salesforce.query.OpportunityQueryResult;
import com.fpx.abebe.salesforce.query.QueryResult;
import com.fpx.abebe.salesforce.query.QueryService;
import com.fpx.abebe.salesforce.query.UserQueryResult;

public class InitializeDatabaseCommand implements Command
{
	private CLI cli;
	public InitializeDatabaseCommand(CLI cli)
	{
		this.setCli(cli);
	}

	public boolean execute() 
	{
		boolean status = false;
		this.getCli().logger.info("Running " + InitializeDatabaseCommand.class);
		if(this.getCli().getAccess() != null)
		{

			try {
				QueryService queryService = new QueryService(getCli().getClientConnection(),
						getCli().getAccess());
				QueryResult<?> result;
				this.getCli().logger.debug("Query " + User.class.getSimpleName());
				result = queryService.queryAll(UserQueryResult.class);
				for(SalesForceObject i : result.getRecords())
				{
					this.getCli().logger.debug("sobject:" + i);
				}
				this.getCli().logger.info("Persisting "+ User.class.getSimpleName() +".");
				DataAccess dataAccess = this.getCli().getDataAccess();
				dataAccess.persist(result);
				this.getCli().logger.debug("Query " + Opportunity.class.getSimpleName());
				result = queryService.queryAll(OpportunityQueryResult.class);
				for(SalesForceObject i : result.getRecords())
				{
					this.getCli().logger.debug("sobject:" + i);
				}
				this.getCli().logger.info("Persisting "+ Opportunity.class.getSimpleName() +".");
				dataAccess.persist(result);
				status = true;
			} 
			catch (ClientProtocolException e) 
			{
				this.getCli().logger.error("Protocol error ",e);
				e.printStackTrace();
			} 
			catch (InstantiationException e) 
			{
				this.getCli().logger.error("Instance creation error ",e);
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				this.getCli().logger.error("Access error ",e);
				e.printStackTrace();
			}
			catch (IOException e) 
			{
				this.getCli().logger.error("I/O error ",e);
				e.printStackTrace();
			}
		}
		else
		{
			this.getCli().logger.error("Must have access token to run this command " + this.getClass().getSimpleName());
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
