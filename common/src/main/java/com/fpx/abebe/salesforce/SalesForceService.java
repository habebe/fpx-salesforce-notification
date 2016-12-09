package com.fpx.abebe.salesforce;

public abstract class SalesForceService 
{
	private SalesForceClientConnection clientConnection;

	public SalesForceService(SalesForceClientConnection clientConnection)
	{
		this.setClientConnection(clientConnection);
	}
	
	public SalesForceClientConnection getClientConnection() {
		return clientConnection;
	}

	public void setClientConnection(SalesForceClientConnection clientConnection) {
		this.clientConnection = clientConnection;
	}
	
	
}
