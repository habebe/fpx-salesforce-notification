package com.fpx.abebe.salesforce.authentication;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.message.BasicHeader;
import org.json.JSONObject;

public class Access
{
	private String token;
	private String signature;
	private String instanceURL;
	private String id;
	private String tokenType;
	private String issuedAt;
	private BasicHeader oAuthHeader;
	private String userId;
	
	public Access(JSONObject jsonObject)
	{
		this.setToken(jsonObject.getString("access_token"));
		this.setSignature(jsonObject.getString("signature"));
		this.setInstanceURL(jsonObject.getString("instance_url"));
		this.setId(jsonObject.getString("id"));
		try 
		{
			URL url = new URL(this.getId());
			String path = url.getPath();
			String[] parts = path.split("/");
			this.setUserId(parts[parts.length-1]);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		this.setTokenType("token_type");
		this.setIssuedAt(jsonObject.getString("issued_at"));
		this.setOAuthHeader(new BasicHeader("Authorization", "OAuth " + this.getToken()));
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getInstanceURL() {
		return instanceURL;
	}
	public void setInstanceURL(String instanceURL) {
		this.instanceURL = instanceURL;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getIssuedAt() {
		return issuedAt;
	}
	public void setIssuedAt(String issuedAt) {
		this.issuedAt = issuedAt;
	}

	public BasicHeader getOAuthHeader() {
		return oAuthHeader;
	}

	public void setOAuthHeader(BasicHeader oAuthHeader) {
		this.oAuthHeader = oAuthHeader;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Access [token=" + token + ", signature=" + signature + ", instanceURL=" + instanceURL + ", id=" + id
				+ ", tokenType=" + tokenType + ", issuedAt=" + issuedAt + ", oAuthHeader=" + oAuthHeader + ", userId="
				+ userId + "]";
	}
	
	
}
