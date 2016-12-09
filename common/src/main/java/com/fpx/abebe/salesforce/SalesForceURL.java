package com.fpx.abebe.salesforce;

import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.authentication.Credential;

public class SalesForceURL 
{
	private final static String loginURL     = "https://login.salesforce.com";
	private final static String grantService = "/services/oauth2/token?grant_type=password";
	private final static String restEndPoint = "/services/data" ;
	private final static String apiVersion   = "/v37.0" ;
	
	public static String getLoginURL() {
		return loginURL;
	}
	public static String getGrantService() {
		return grantService;
	}
	public static String getRestEndPoint() {
		return restEndPoint;
	}
	public static String getApiVersion() {
		return apiVersion;
	}	
	
	public static String getLoginURL(Credential credential)
	{
		String url = String.format("%s%s&client_id=%s&client_secret=%s&username=%s&password=%s",
				SalesForceURL.getLoginURL(),
				SalesForceURL.getGrantService(),
				credential.getClientId(),
				credential.getClientSecret(),
				credential.getUserName(),
				credential.getPassword()
				);
		return url;
	}

	public static String getQueryURL(Access access)
	{
		String url = String.format("%s%s%s/query",
				access.getInstanceURL(),
				SalesForceURL.getRestEndPoint(),
				SalesForceURL.getApiVersion()
				);
		return url;
	}
	
}
