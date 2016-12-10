package com.fpx.abebe.salesforce.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NotificationMessageTable")
public class NotificationMessage 
{
	@Id
	private String id;
	private String userId;
	private String opportunityOwnerId;
	private int criteriaId;
	private String opportunityId;
	private Date timeEvaluated;
	private String message;
	
	public void setUser(User user)
	{
		this.setUserId(user.getId());
	}
	public void setOwner(User user)
	{
		this.setOpportunityOwnerId(user.getId());
	}
	public void setOpportunity(Opportunity opportunity)
	{
		this.setOpportunityId(opportunity.getId());
		this.setOpportunityOwnerId(opportunity.getOwnerId());
	}
	public void setCriteria(NotificationCriteria criteria)
	{
		this.setCriteriaId(criteria.getId());
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOpportunityOwnerId() {
		return opportunityOwnerId;
	}
	public void setOpportunityOwnerId(String opportunityOwnerId) {
		this.opportunityOwnerId = opportunityOwnerId;
	}
	public int getCriteriaId() {
		return criteriaId;
	}
	public void setCriteriaId(int criteriaId) {
		this.criteriaId = criteriaId;
	}
	public String getOpportunityId() {
		return opportunityId;
	}
	public void setOpportunityId(String opportunityId) {
		this.opportunityId = opportunityId;
	}
	public Date getTimeEvaluated() {
		return timeEvaluated;
	}
	public void setTimeEvaluated(Date timeEvaluated) {
		this.timeEvaluated = timeEvaluated;
	}
	@Override
	public String toString() {
		return "NotificationMessage [id=" + id + ", userId=" + userId + ", opportunityOwnerId=" + opportunityOwnerId
				+ ", criteriaId=" + criteriaId + ", opportunityId=" + opportunityId + ", timeEvaluated=" + timeEvaluated
				+ "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public static String generateUniqueId(NotificationCriteria criteria,
				Opportunity opportunity,
				User user,
				User owner
			)
	{
		return String.format("%d-%s-%s-%s",
				criteria.getId(),
				opportunity.getId(),
				user.getId(),
				owner.getId()
				);
	}
	
	public void generateId() 
	{
		this.id = String.format("%d-%s-%s-%s",
				this.getCriteriaId(),this.getOpportunityId(),
				this.getUserId(),this.getOpportunityOwnerId()
				);
	}
}
