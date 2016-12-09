package com.fpx.abebe.salesforce.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "OpportunityTable")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Opportunity extends SalesForceObject
{
	@JsonProperty(value="HasOpenActivity")
	private boolean hasOpenActivity;
	@JsonProperty(value="LastActivityDate")
	private Date lastActivityDate;
	@JsonProperty(value="LastReferencedDate")
	private Date lastReferencedDate;
	@JsonProperty(value="LastModifiedById")
	private String lastModifiedById;
	@JsonProperty(value="OwnerId")
	private String ownerId;
	@JsonProperty(value="CloseDate")
	private Date closeDate;
	@JsonProperty(value="OrderNumber__c")
	private String orderNumber__c;
	@JsonProperty(value="Type")
	private String type;
	@JsonProperty(value="Description")
	private String description;
	@JsonProperty(value="DeliveryInstallationStatus__c")
	private String deliveryInstallationStatus__c;
	@JsonProperty(value="CampaignId")
	private String campaignId;
	@JsonProperty(value="LastModifiedDate")
	private Date lastModifiedDate;
	@JsonProperty(value="ForecastCategoryName")
	private String forecastCategoryName;
	@JsonProperty(value="CreatedById")
	private String createdById;
	@JsonProperty(value="MainCompetitors__c")
	private String mainCompetitors__c;
	@JsonProperty(value="LastViewedDate")
	private Date lastViewedDate;
	@JsonProperty(value="IsDeleted")
	private boolean isDeleted;
	@JsonProperty(value="AccountId")
	private String accountId;
	@JsonProperty(value="Fiscal")
	private String fiscal;
	@JsonProperty(value="Name")
	private String name;
	@JsonProperty(value="CurrentGenerators__c")
	private String currentGenerators__c;
	@JsonProperty(value="IsWon")
	private boolean isWon;
	@JsonProperty(value="TrackingNumber__c")
	private String trackingNumber__c;
	@JsonProperty(value="Amount")
	private double amount;
	@JsonProperty(value="LeadSource")
	private String leadSource;
	@JsonProperty(value="HasOverdueTask")
	private boolean hasOverdueTask;
	@JsonProperty(value="IsPrivate")
	private boolean isPrivate;
	@JsonProperty(value="TotalOpportunityQuantity")
	private String totalOpportunityQuantity;
	@JsonProperty(value="Pricebook2Id")
	private String pricebook2Id;
	@JsonProperty(value="StageName")
	private String stageName;
	@JsonProperty(value="Probability")
	private double probability;
	@JsonProperty(value="ExpectedRevenue")
	private double expectedRevenue;
	@JsonProperty(value="FiscalQuarter")
	private int fiscalQuarter;
	@JsonProperty(value="HasOpportunityLineItem")
	private boolean hasOpportunityLineItem;
	@JsonProperty(value="IsClosed")
	private boolean isClosed;
	@JsonProperty(value="NextStep")
	private String nextStep;
	@JsonProperty(value="SystemModstamp")
	private String systemModstamp;
	@JsonProperty(value="CreatedDate")
	private Date createdDate;
	@JsonProperty(value="ForecastCategory")
	private String forecastCategory;
	@Id
	@JsonProperty(value="Id")
	private String id;
	@JsonProperty(value="FiscalYear")
	private int fiscalYear;
	
	
	@JsonIgnore
	public void setLastActivityDate(Date lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}
	@JsonIgnore
	public void setLastReferencedDate(Date lastReferencedDate) {
		this.lastReferencedDate = lastReferencedDate;
	}
	@JsonIgnore
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}
	@JsonIgnore
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	@JsonIgnore
	public void setLastViewedDate(Date lastViewedDate) {
		this.lastViewedDate = lastViewedDate;
	}
	@JsonIgnore
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public boolean isHasOpenActivity() {
		return hasOpenActivity;
	}
	public void setHasOpenActivity(boolean hasOpenActivity) {
		this.hasOpenActivity = hasOpenActivity;
	}
	
	public Date getLastActivityDate() {
		return lastActivityDate;
	}
	public void setLastActivityDate(String lastActivityDate) {
		this.lastActivityDate = this.parseDateFromString(lastActivityDate);
	}
	public Date getLastReferencedDate() {
		return lastReferencedDate;
	}
	public void setLastReferencedDate(String lastReferencedDate) {
		this.lastReferencedDate = this.parseDateFromString(lastReferencedDate);
	}
	public String getLastModifiedById() {
		return lastModifiedById;
	}
	public void setLastModifiedById(String lastModifiedById) {
		this.lastModifiedById = lastModifiedById;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public Date getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = this.parseDateFromString(closeDate);
	}
	public String getOrderNumber__c() {
		return orderNumber__c;
	}
	public void setOrderNumber__c(String orderNumber__c) {
		this.orderNumber__c = orderNumber__c;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDeliveryInstallationStatus__c() {
		return deliveryInstallationStatus__c;
	}
	public void setDeliveryInstallationStatus__c(String deliveryInstallationStatus__c) {
		this.deliveryInstallationStatus__c = deliveryInstallationStatus__c;
	}
	public String getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = this.parseDateFromString(lastModifiedDate);
	}
	public String getForecastCategoryName() {
		return forecastCategoryName;
	}
	public void setForecastCategoryName(String forecastCategoryName) {
		this.forecastCategoryName = forecastCategoryName;
	}
	public String getCreatedById() {
		return createdById;
	}
	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}
	public String getMainCompetitors__c() {
		return mainCompetitors__c;
	}
	public void setMainCompetitors__c(String mainCompetitors__c) {
		this.mainCompetitors__c = mainCompetitors__c;
	}
	public Date getLastViewedDate() {
		return lastViewedDate;
	}
	public void setLastViewedDate(String lastViewedDate) {
		this.lastViewedDate = this.parseDateFromString(lastViewedDate);
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getFiscal() {
		return fiscal;
	}
	public void setFiscal(String fiscal) {
		this.fiscal = fiscal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCurrentGenerators__c() {
		return currentGenerators__c;
	}
	public void setCurrentGenerators__c(String currentGenerators__c) {
		this.currentGenerators__c = currentGenerators__c;
	}
	public boolean isWon() {
		return isWon;
	}
	public void setWon(boolean isWon) {
		this.isWon = isWon;
	}
	public String getTrackingNumber__c() {
		return trackingNumber__c;
	}
	public void setTrackingNumber__c(String trackingNumber__c) {
		this.trackingNumber__c = trackingNumber__c;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getLeadSource() {
		return leadSource;
	}
	public void setLeadSource(String leadSource) {
		this.leadSource = leadSource;
	}
	public boolean isHasOverdueTask() {
		return hasOverdueTask;
	}
	public void setHasOverdueTask(boolean hasOverdueTask) {
		this.hasOverdueTask = hasOverdueTask;
	}
	public boolean isPrivate() {
		return isPrivate;
	}
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public String getTotalOpportunityQuantity() {
		return totalOpportunityQuantity;
	}
	public void setTotalOpportunityQuantity(String totalOpportunityQuantity) {
		this.totalOpportunityQuantity = totalOpportunityQuantity;
	}
	public String getPricebook2Id() {
		return pricebook2Id;
	}
	public void setPricebook2Id(String pricebook2Id) {
		this.pricebook2Id = pricebook2Id;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public double getProbability() {
		return probability;
	}
	public void setProbability(double probability) {
		this.probability = probability;
	}
	public double getExpectedRevenue() {
		return expectedRevenue;
	}
	public void setExpectedRevenue(double expectedRevenue) {
		this.expectedRevenue = expectedRevenue;
	}
	public int getFiscalQuarter() {
		return fiscalQuarter;
	}
	public void setFiscalQuarter(int fiscalQuarter) {
		this.fiscalQuarter = fiscalQuarter;
	}
	public boolean isHasOpportunityLineItem() {
		return hasOpportunityLineItem;
	}
	public void setHasOpportunityLineItem(boolean hasOpportunityLineItem) {
		this.hasOpportunityLineItem = hasOpportunityLineItem;
	}
	public boolean isClosed() {
		return isClosed;
	}
	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}
	public String getNextStep() {
		return nextStep;
	}
	public void setNextStep(String nextStep) {
		this.nextStep = nextStep;
	}
	public String getSystemModstamp() {
		return systemModstamp;
	}
	public void setSystemModstamp(String systemModstamp) {
		this.systemModstamp = systemModstamp;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = this.parseDateFromString(createdDate);
	}
	public String getForecastCategory() {
		return forecastCategory;
	}
	public void setForecastCategory(String forecastCategory) {
		this.forecastCategory = forecastCategory;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(int fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	@Override
	public String toString() {
		return "Opportunity [hasOpenActivity=" + hasOpenActivity + ", lastActivityDate=" + lastActivityDate
				+ ", lastReferencedDate=" + lastReferencedDate + ", lastModifiedById=" + lastModifiedById + ", ownerId="
				+ ownerId + ", closeDate=" + closeDate + ", orderNumber__c=" + orderNumber__c + ", type=" + type
				+ ", description=" + description + ", deliveryInstallationStatus__c=" + deliveryInstallationStatus__c
				+ ", campaignId=" + campaignId + ", lastModifiedDate=" + lastModifiedDate + ", forecastCategoryName="
				+ forecastCategoryName + ", createdById=" + createdById + ", mainCompetitors__c=" + mainCompetitors__c
				+ ", lastViewedDate=" + lastViewedDate + ", isDeleted=" + isDeleted + ", accountId=" + accountId
				+ ", fiscal=" + fiscal + ", name=" + name + ", currentGenerators__c=" + currentGenerators__c
				+ ", isWon=" + isWon + ", trackingNumber__c=" + trackingNumber__c + ", amount=" + amount
				+ ", leadSource=" + leadSource + ", hasOverdueTask=" + hasOverdueTask + ", isPrivate=" + isPrivate
				+ ", totalOpportunityQuantity=" + totalOpportunityQuantity + ", pricebook2Id=" + pricebook2Id
				+ ", stageName=" + stageName + ", probability=" + probability + ", expectedRevenue=" + expectedRevenue
				+ ", fiscalQuarter=" + fiscalQuarter + ", hasOpportunityLineItem=" + hasOpportunityLineItem
				+ ", isClosed=" + isClosed + ", nextStep=" + nextStep + ", systemModstamp=" + systemModstamp
				+ ", createdDate=" + createdDate + ", forecastCategory=" + forecastCategory + ", id=" + id
				+ ", fiscalYear=" + fiscalYear + "]";
	}
}
