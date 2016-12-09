package com.fpx.abebe.salesforce.authentication;

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
	
	public Access(JSONObject jsonObject)
	{
		this.setToken(jsonObject.getString("access_token"));
		this.setSignature(jsonObject.getString("signature"));
		this.setInstanceURL(jsonObject.getString("instance_url"));
		this.setId(jsonObject.getString("id"));
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
	@Override
	public String toString() {
		return "Access [token=" + token + ", signature=" + signature + ", instanceURL=" + instanceURL + ", id=" + id
				+ ", tokenType=" + tokenType + ", issuedAt=" + issuedAt + "]";
	}

	public BasicHeader getOAuthHeader() {
		return oAuthHeader;
	}

	public void setOAuthHeader(BasicHeader oAuthHeader) {
		this.oAuthHeader = oAuthHeader;
	}
	
	
	
	
	
	
	/*
	{"access_token":
		
		"00D41000001OwxK!ARIAQMNlfxbk0q.7S_HxY8ssUfo4Wd0b8.SDuzuOOLP4E2oFFrLWYyW.836S4.37W5xxmKZwCTx7eXMcJeUfxduTl5Op0MGS",
		"signature":"UWYzr7N2qPhFIkdJqjrDHRhiKxBAm03t7Xnw+A/cPq0=","instance_url":"https://na35.salesforce.com",
		"id":"https://login.salesforce.com/id/00D41000001OwxKEAS/00541000001LmWGAA0","token_type":"Bearer",
		"issued_at":"1481134835673"}
		
		*/

}
