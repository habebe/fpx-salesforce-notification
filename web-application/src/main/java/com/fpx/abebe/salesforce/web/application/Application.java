package com.fpx.abebe.salesforce.web.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.http.client.ClientProtocolException;

import com.fpx.abebe.salesforce.SalesForceClientConnection;
import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.authentication.Credential;
import com.fpx.abebe.salesforce.authentication.GrantService;
import com.fpx.abebe.salesforce.database.DataAccess;

public class Application 
{
	private static Application instance = null;
	private static final String propertyFileName = "/WEB-INF/properties/salesforce.properties";
	private String clientId = null;
	private String clientSecret = null;
	private SalesForceClientConnection salesForceClientConnection = new SalesForceClientConnection();
	private DataAccess dataAccess;

	public static synchronized Application getInstance(ServletContext servletContext)
	{
		if(Application.instance == null)
		{
			try 
			{
				Application.instance = new Application(servletContext);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		return instance;
	}

	private Application(ServletContext servletContext) throws IOException
	{
		this.initialize(servletContext);
		this.setDataAccess(new DataAccess());
	}

	private void initialize(ServletContext servletContext) throws IOException
	{
		Properties properties = new Properties();
		InputStream inputStream = servletContext.getResourceAsStream(Application.getPropertyFileName());
		properties.load(inputStream);
		this.setClientId(properties.getProperty("clientId"));
		this.setClientSecret(properties.getProperty("clientSecret"));
	}
	

	public static String getPropertyFileName() 
	{
		return propertyFileName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public Access login(String userName,String password)
	{
		Credential credential = new Credential();
		credential.setClientId(this.getClientId());
		credential.setClientSecret(this.getClientSecret());
		credential.setUserName(userName);
		credential.setPassword(password);
		GrantService grantService = new GrantService(getSalesForceClientConnection());
		Access access = null;
		try 
		{
			access = grantService.login(credential);
		} 
		catch (ClientProtocolException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return access;
	}

	public SalesForceClientConnection getSalesForceClientConnection() {
		return salesForceClientConnection;
	}

	public DataAccess getDataAccess() {
		return dataAccess;
	}

	private void setDataAccess(DataAccess dataAccess) {
		this.dataAccess = dataAccess;
	}
}
