package com.fpx.abebe.salesforce.authentication;

import java.util.Properties;

public class Credential 
{
	private String userName;
	private String password;
	private String clientId;
	private String clientSecret;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	@Override
	public String toString() {
		return "Credential [userName=" + userName + ", password=" + password + ", clientId=" + clientId
				+ ", clientSecret=" + clientSecret + "]";
	}

	public void setUsingProperty(Properties properties )
	{
		this.setUserName(properties.getProperty("userName",this.getUserName()));
		this.setPassword(properties.getProperty("password",this.getPassword()));
		this.setClientId(properties.getProperty("clientId",this.getClientId()));
		this.setClientSecret(properties.getProperty("clientSecret", this.getClientSecret()));
	}

}