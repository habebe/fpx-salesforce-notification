package com.fpx.abebe.salesforce.query;

import com.fpx.abebe.salesforce.model.Opportunity;

public class OpportunityQueryResult extends QueryResult<Opportunity> 
{
	private final static String allPropertiesNames = 
			"AccountId,Amount,CampaignId,CloseDate,CreatedById,CreatedDate,"
			+ "CurrentGenerators__c,DeliveryInstallationStatus__c,Description,ExpectedRevenue,"
			+ "Fiscal,FiscalQuarter,FiscalYear,ForecastCategory,ForecastCategoryName,"
			+ "HasOpenActivity,HasOpportunityLineItem,HasOverdueTask,Id,IsClosed,IsDeleted,"
			+ "IsPrivate,IsWon,LastActivityDate,LastModifiedById,LastModifiedDate,"
			+ "LastReferencedDate,LastViewedDate,LeadSource,MainCompetitors__c,Name,"
			+ "NextStep,OrderNumber__c,OwnerId,Pricebook2Id,Probability,StageName,"
			+ "SystemModstamp,TotalOpportunityQuantity,TrackingNumber__c,Type";
	private final static String objectName = "Opportunity";
	
	@Override
	public String getAllPropertiesNames() {
		return allPropertiesNames;
	}

	@Override	
	public String getObjectName() {
		return objectName;
	}	
}
