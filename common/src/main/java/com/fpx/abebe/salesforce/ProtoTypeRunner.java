package com.fpx.abebe.salesforce;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.authentication.Credential;
import com.fpx.abebe.salesforce.authentication.GrantService;
import com.fpx.abebe.salesforce.model.PushTopic;
import com.fpx.abebe.salesforce.query.PushTopicQueryResult;
import com.fpx.abebe.salesforce.query.QueryResult;
import com.fpx.abebe.salesforce.query.QueryService;
import com.fpx.abebe.salesforce.restapi.RestService;

public class ProtoTypeRunner 
{
	private final static String USERNAME = "hay.knock@outlook.com";
	private final static String PASSWORD = "fpx.developer.1";
	private final static String CLIENTID     = "3MVG9szVa2RxsqBahloiiIJfFb3FmU5W4Repa7D0PwabFtBq99KqbB87KPnfPMH9aSzW9U61hFW1bAlLb8TSn";
	private final static String CLIENTSECRET = "6560532460942854266";
	private final static SalesForceClientConnection connection = new SalesForceClientConnection();
	
	public static Access getAccess() throws ClientProtocolException, IOException
	{
		Credential credential = new Credential();
		credential.setClientId(CLIENTID);
		credential.setClientSecret(CLIENTSECRET);
		credential.setUserName(USERNAME);
		credential.setPassword(PASSWORD);
		GrantService grantService = new GrantService(connection);
		Access access = grantService.login(credential);
		return access;
	}
	
	
	public static void queryPushTopic() throws ClientProtocolException, IOException, InstantiationException, IllegalAccessException
	{
		Access access = getAccess();
		System.out.println("Access:" + access);
		QueryService queryService = new QueryService(connection,access);
		QueryResult<?> result = queryService.queryAll(PushTopicQueryResult.class);
		System.out.println("Push Topic Result: " + result);
	}
	
	public static void postPushTopic() throws ClientProtocolException, IOException
	{
		Access access = getAccess();
		System.out.println("Access:" + access);
		RestService service = new RestService(connection,access);
		PushTopic pushTopic = new PushTopic();
		pushTopic.setName("fpx.sf.opp.noti");
		pushTopic.setDescription("PushTopic description used by data service.");
		pushTopic.setQuery("SELECT "
				+ "AccountId,Amount,CampaignId,CloseDate,CreatedById,CreatedDate,"
				+ "CurrentGenerators__c,DeliveryInstallationStatus__c,ExpectedRevenue,"
				+ "Fiscal,FiscalQuarter,FiscalYear,ForecastCategory,ForecastCategoryName,"
				+ "HasOpenActivity,HasOpportunityLineItem,HasOverdueTask,Id,IsClosed,IsDeleted,"
				+ "IsPrivate,IsWon,LastActivityDate,LastModifiedById,LastModifiedDate,"
				+ "LastReferencedDate,LastViewedDate,LeadSource,MainCompetitors__c,Name,"
				+ "NextStep,OrderNumber__c,OwnerId,Pricebook2Id,Probability,StageName,"
				+ "SystemModstamp,TotalOpportunityQuantity,TrackingNumber__c,Type FROM OPPORTUNITY");
		pushTopic.setApiVersion(SalesForceURL.getApiVersionNumber());
		pushTopic.setNotifyForFields("All");
		pushTopic.setNotifyForOperationCreate(true);
		pushTopic.setNotifyForOperationDelete(true);
		pushTopic.setNotifyForOperationUndelete(true);
		pushTopic.setNotifyForOperationUpdate(true);
		boolean response = service.post(pushTopic);
		System.out.println(response);
		
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException, InstantiationException, IllegalAccessException 
	{
		queryPushTopic();
		postPushTopic();
		queryPushTopic();
	}
}
