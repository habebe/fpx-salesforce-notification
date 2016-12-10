
package com.fpx.abebe.salesforce.model;

import java.util.Date;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PushTopic extends SalesForceObject
{
	@JsonProperty(value="SystemModstamp")
	private Date systemModstamp;
	@JsonProperty(value="Description")
	private String description;
	@JsonProperty(value="LastModifiedDate")
	private Date lastModifiedDate;
	@JsonProperty(value="NotifyForOperationCreate")
	private boolean notifyForOperationCreate;
	@JsonProperty(value="LastModifiedById")
	private String lastModifiedById;
	@JsonProperty(value="ApiVersion")
	private double apiVersion;
	@JsonProperty(value="NotifyForOperationUndelete")
	private boolean notifyForOperationUndelete;
	@JsonProperty(value="CreatedById")
	private String createdById;
	@JsonProperty(value="NotifyForOperationUpdate")
	private boolean notifyForOperationUpdate;
	@JsonProperty(value="IsDeleted")
	private boolean isDeleted;
	@JsonProperty(value="NotifyForOperationDelete")
	private boolean notifyForOperationDelete;
	@JsonProperty(value="CreatedDate")
	private Date createdDate;
	@JsonProperty(value="NotifyForOperations")
	private String notifyForOperations;
	@JsonProperty(value="Query")
	private String query;
	@JsonProperty(value="NotifyForFields")
	private String notifyForFields;
	@JsonProperty(value="Id")
	private String id;
	@JsonProperty(value="IsActive")
	private boolean isActive;
	@JsonProperty(value="Name")
	private String name;
	
	@JsonIgnore
	public void setSystemModstamp(Date systemModstamp) {
		this.systemModstamp = systemModstamp;
	}
	@JsonIgnore
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	@JsonIgnore
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getSystemModstamp() {
		return systemModstamp;
	}
	public void setSystemModstamp(String systemModstamp) {
		this.systemModstamp = this.parseDateFromString(systemModstamp);
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = this.parseDateFromString(lastModifiedDate);
	}
	public boolean isNotifyForOperationCreate() {
		return notifyForOperationCreate;
	}
	public void setNotifyForOperationCreate(boolean notifyForOperationCreate) {
		this.notifyForOperationCreate = notifyForOperationCreate;
	}
	public String getLastModifiedById() {
		return lastModifiedById;
	}
	public void setLastModifiedById(String lastModifiedById) {
		this.lastModifiedById = lastModifiedById;
	}
	public double getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(double apiVersion) {
		this.apiVersion = apiVersion;
	}
	public boolean isNotifyForOperationUndelete() {
		return notifyForOperationUndelete;
	}
	public void setNotifyForOperationUndelete(boolean notifyForOperationUndelete) {
		this.notifyForOperationUndelete = notifyForOperationUndelete;
	}
	public String getCreatedById() {
		return createdById;
	}
	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}
	public boolean isNotifyForOperationUpdate() {
		return notifyForOperationUpdate;
	}
	public void setNotifyForOperationUpdate(boolean notifyForOperationUpdate) {
		this.notifyForOperationUpdate = notifyForOperationUpdate;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public boolean isNotifyForOperationDelete() {
		return notifyForOperationDelete;
	}
	public void setNotifyForOperationDelete(boolean notifyForOperationDelete) {
		this.notifyForOperationDelete = notifyForOperationDelete;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = this.parseDateFromString(createdDate);
	}
	public String getNotifyForOperations() {
		return notifyForOperations;
	}
	public void setNotifyForOperations(String notifyForOperations) {
		this.notifyForOperations = notifyForOperations;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getNotifyForFields() {
		return notifyForFields;
	}
	public void setNotifyForFields(String notifyForFields) {
		this.notifyForFields = notifyForFields;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public JSONObject toJsonObject()
	{
		JSONObject object = new JSONObject();
		object.put("Description", this.getDescription());
		object.put("ApiVersion", this.getApiVersion());
		object.put("NotifyForOperationCreate", this.isNotifyForOperationCreate());
		object.put("NotifyForOperationUndelete", this.isNotifyForOperationUndelete());
		object.put("NotifyForOperationDelete", this.isNotifyForOperationDelete());
		object.put("NotifyForOperationUpdate", this.isNotifyForOperationUpdate());
	//	object.put("NotifyForOperations", this.getNotifyForOperations());
		object.put("NotifyForFields", this.getNotifyForFields());
		object.put("Query", this.getQuery());
		object.put("Name", this.getName());
		return object;
	}
	
	@Override
	public String toString() {
		return "PushTopic [systemModstamp=" + systemModstamp + ", description=" + description + ", lastModifiedDate="
				+ lastModifiedDate + ", notifyForOperationCreate=" + notifyForOperationCreate + ", lastModifiedById="
				+ lastModifiedById + ", apiVersion=" + apiVersion + ", notifyForOperationUndelete="
				+ notifyForOperationUndelete + ", createdById=" + createdById + ", notifyForOperationUpdate="
				+ notifyForOperationUpdate + ", isDeleted=" + isDeleted + ", notifyForOperationDelete="
				+ notifyForOperationDelete + ", createdDate=" + createdDate + ", notifyForOperations="
				+ notifyForOperations + ", query=" + query + ", notifyForFields=" + notifyForFields + ", id=" + id
				+ ", isActive=" + isActive + ", name=" + name + "]";
	}
}