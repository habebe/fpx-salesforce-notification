package com.fpx.abebe.salesforce.query;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpx.abebe.salesforce.SalesForceClientConnection;
import com.fpx.abebe.salesforce.SalesForceService;
import com.fpx.abebe.salesforce.SalesForceURL;
import com.fpx.abebe.salesforce.authentication.Access;

public class QueryService extends SalesForceService
{
	private Access access;	
	public QueryService(SalesForceClientConnection clientConnection,Access access)
	{
		super(clientConnection);
		this.setAccess(access);
	}

	public Access getAccess() {
		return access;
	}

	public void setAccess(Access access) {
		this.access = access;
	}

	public <T extends QueryResult<?>> QueryResult<?> queryAll(Class<T> clazz) 
			throws ClientProtocolException, IOException, InstantiationException, IllegalAccessException
	{
		QueryResult<?> result = clazz.newInstance(); 
		String queryUrl = String.format("%s/?q=SELECT+%s+FROM+%s",
				SalesForceURL.getQueryURL(access),
				result.getAllPropertiesNames(),
				result.getObjectName()
				);
		HttpGet httpGet = new HttpGet(queryUrl);
		httpGet.addHeader(access.getOAuthHeader());
		HttpResponse response = this.getClientConnection().getClient().execute(httpGet);
		String responseString = EntityUtils.toString(response.getEntity());
		ObjectMapper mapper = new ObjectMapper();
		result = mapper.readValue(responseString, 
				result.getClass());
		return result;
	}

	
	public <T extends QueryResult<?>> String queryRawAll(Class<T> clazz) 
			throws ClientProtocolException, IOException, InstantiationException, IllegalAccessException
	{
		QueryResult<?> result = clazz.newInstance(); 
		String queryUrl = String.format("%s/?q=SELECT+%s+FROM+%s",
				SalesForceURL.getQueryURL(access),
				result.getAllPropertiesNames(),
				result.getObjectName()
				);
		HttpGet httpGet = new HttpGet(queryUrl);
		httpGet.addHeader(access.getOAuthHeader());
		HttpResponse response = this.getClientConnection().getClient().execute(httpGet);
		String responseString = EntityUtils.toString(response.getEntity());
		return responseString;
	}
}
