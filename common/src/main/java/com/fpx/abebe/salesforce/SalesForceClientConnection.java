package com.fpx.abebe.salesforce;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class SalesForceClientConnection 
{
	private HttpClient client;
	public SalesForceClientConnection()
	{
		this.setClient(HttpClientBuilder.create().build());
	}
	
	public HttpClient getClient() {
		return client;
	}

	public void setClient(HttpClient client) {
		this.client = client;
	}
	
	
}
