package com.fpx.abebe.salesforce.notification;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.authentication.GrantService;

public class AuthenticateCommand implements Command
{
	private CLI cli;
	public AuthenticateCommand(CLI cli)
	{
		this.setCli(cli);
	}
	
	public boolean execute() 
	{
		this.getCli().logger.info("Running " + AuthenticateCommand.class);
		GrantService grantService = new GrantService(this.getCli().getClientConnection());
		this.getCli().setAccess(null);
		try 
		{
			Access access = grantService.login(this.getCli().getCredential()); 
			this.cli.setAccess(access);
		} 
		catch (ClientProtocolException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		if(this.cli.getAccess() == null)
		{
			this.getCli().logger.error("Unable to get access token. Invalid credentials.");
		}
		else
		{
			this.getCli().logger.info("Access granted using " + this.getCli().getAccess());
		}
		return (this.cli.getAccess() != null);
	}

	public CLI getCli() {
		return cli;
	}

	public void setCli(CLI cli) {
		this.cli = cli;
	}
}
