package com.fpx.abebe.salesforce.dataservice.task;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.authentication.GrantService;
import com.fpx.abebe.salesforce.dataservice.DataService;

public class AuthenticateServiceTask extends AbstractServiceTask
{
	public AuthenticateServiceTask(DataService dataService)
	{
		super(dataService);
	}
	
	public boolean execute() 
	{
		this.getLogger().info("Running " + this.getClass());
		GrantService grantService = new GrantService(this.getDataService().getClientConnection());
		this.getDataService().setAccess(null);
		Access access = null;
		try 
		{
			access = grantService.login(this.getDataService().getCredential()); 
			this.getDataService().setAccess(access);
		} 
		catch (ClientProtocolException e) 
		{
			e.printStackTrace();
			this.getLogger().error("ClientProtocolException ",e);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			this.getLogger().error("IOException ",e);
		}
		if(access == null)
		{
			this.getLogger().error("Unable to get access token. Invalid credentials.");
		}
		else
		{
			this.getLogger().info("Access granted using " + access);
		}
		return (access != null);
	}

	public void run() 
	{
		this.execute();
	}
}
