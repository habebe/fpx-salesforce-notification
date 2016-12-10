package com.fpx.abebe.salesforce.dataservice.task;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.fpx.abebe.salesforce.SalesForceURL;
import com.fpx.abebe.salesforce.dataservice.DataService;
import com.fpx.abebe.salesforce.model.PushTopic;
import com.fpx.abebe.salesforce.query.PushTopicQueryResult;
import com.fpx.abebe.salesforce.query.QueryResult;
import com.fpx.abebe.salesforce.query.QueryService;
import com.fpx.abebe.salesforce.restapi.RestService;

public class RegisterPushTopicServiceTask extends AbstractServiceTask
{

	public RegisterPushTopicServiceTask(DataService dataService) 
	{
		super(dataService);
	}

	public boolean execute() 
	{
		boolean status = false;
		this.getLogger().info("Running " + this.getClass());
		if(this.getAccess() != null)
		{

			try {
				QueryService queryService = new QueryService(
						getDataService().getClientConnection(),
						this.getAccess());
				QueryResult<?> result;
				this.getLogger().debug("Query " + PushTopic.class.getSimpleName());
				result = queryService.queryAll(PushTopicQueryResult.class);
				List<?> objects = result.getRecords();
				Iterator<?> iterator = objects.iterator();
				boolean foundMatch = false;
				while(iterator.hasNext() && (foundMatch == false))
				{
					PushTopic pushTopic = (PushTopic)iterator.next();
					foundMatch = (pushTopic.getName().equals(this.getDataService().getPushTopicName()));
					if(foundMatch)
						this.getDataService().setPushTopic(pushTopic);
				}
				status = foundMatch;
				if(foundMatch == false)
				{
					status = this.createPushTopic();
				}
				else
				{
					getLogger().info("Push Topic exists:" + this.getDataService().getPushTopic().getName());
				}
			} 
			catch (ClientProtocolException e) 
			{
				this.getLogger().error("Protocol error ",e);
				e.printStackTrace();
			} 
			catch (InstantiationException e) 
			{
				this.getLogger().error("Instance creation error ",e);
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				this.getLogger().error("Access error ",e);
				e.printStackTrace();
			}
			catch (IOException e) 
			{
				this.getLogger().error("I/O error ",e);
				e.printStackTrace();
			}
		}
		else
		{
			this.getLogger().error("Must have access token to run this command " + this.getClass().getSimpleName());
		}
		return status;
	}

	private boolean createPushTopic() throws ClientProtocolException, IOException 
	{
		boolean status = false;
		
		RestService service = new RestService(
				this.getDataService().getClientConnection(),
				this.getDataService().getAccess());
		PushTopic pushTopic = new PushTopic();
		pushTopic.setName(this.getDataService().getPushTopicName());
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
		getLogger().info("Creating Push Topic" + pushTopic.getName());
		status = service.post(pushTopic);
		if(status == true)
			this.getDataService().setPushTopic(pushTopic);
		return status;
	}
}
