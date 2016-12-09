package com.fpx.abebe.salesforce.authentication;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.fpx.abebe.salesforce.SalesForceClientConnection;
import com.fpx.abebe.salesforce.SalesForceService;
import com.fpx.abebe.salesforce.SalesForceURL;

public class GrantService extends SalesForceService
{
	public GrantService(SalesForceClientConnection clientConnection)
	{
		super(clientConnection);
	}
	
	public Access login(Credential credential) throws ClientProtocolException, IOException
	{
		Access access = null;
		String loginUrl = SalesForceURL.getLoginURL(credential);
		HttpPost httpPost = new HttpPost(loginUrl);
		HttpResponse response = this.getClientConnection().getClient().execute(httpPost);
		if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
		{
			String responseResult = EntityUtils.toString(response.getEntity());
			JSONObject jsonObject = (JSONObject) new JSONTokener(responseResult).nextValue();
			access = new Access(jsonObject);		
		}
		httpPost.releaseConnection();
		return access;
	}
}
