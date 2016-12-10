package com.fpx.abebe.salesforce;

import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.authentication.Credential;

public class SalesForceURL 
{
	private final static String loginURL     = "https://login.salesforce.com";
	private final static String grantService = "/services/oauth2/token?grant_type=password";
	private final static String restEndPoint = "/services/data" ;
	private final static double apiVersionNumber = 37.0;
	private final static String apiVersion    = "/v" +  String.format("%2.1f",apiVersionNumber);
	private final static String cometdEndpoint = "/cometd/" + String.format("%2.1f",apiVersionNumber);

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
	public static double getApiVersionNumber() {
		return apiVersionNumber;
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
	public static String getSObjectURL(Access access,String objectType) 
	{

		return String.format("%s%s%s/sobjects/%s",
				access.getInstanceURL(),
				SalesForceURL.getRestEndPoint(),
				SalesForceURL.getApiVersion(),
				objectType
				);
	}
	public static String getCometdEndpoint() 
	{
		return cometdEndpoint;
	}
	public static String getStreamingEndpoint(Access access)
	{
		return String.format("%s%s",
				access.getInstanceURL(),
				SalesForceURL.getCometdEndpoint()
				);
	}
	public static String getStreamingChannelUrl(String pushTopicName) 
	{
		return String.format("/topic/%s",pushTopicName);
	}
	

}
