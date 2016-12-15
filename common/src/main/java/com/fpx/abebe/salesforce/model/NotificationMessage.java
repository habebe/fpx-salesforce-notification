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
	private int criteriaId;
	private String opportunityId;


	private String recipientId;
	private String opportunityOwnerId;
	private String criteriaOwnerId;
	private String recipientName;
	private String opportunityOwnerName;
	private String criteriaOwnerName;

	private Date timeEvaluated;
	private String message;

	public void setValue(
			NotificationCriteria criteria,
			Opportunity opportunity,
			User recipient,
			User opportunityOwner,
			User criteriaOwner
			)
	{
		this.setCriteria(criteria);
		this.setOpportunity(opportunity);
		this.setRecipient(recipient);
		this.setOpportunityOwner(opportunityOwner);
		this.setCriteriaOwner(criteriaOwner);
	}

	public void setOpportunity(Opportunity opportunity)
	{
		this.opportunityId = opportunity.getId();
	}

	public void setRecipient(User user)
	{
		this.recipientId = user.getId();
		this.recipientName = user.getName();
	}

	public void setOpportunityOwner(User user)
	{
		this.opportunityOwnerId = user.getId();
		this.opportunityOwnerName = user.getName();
	}

	public void setCriteriaOwner(User user)
	{
		this.criteriaOwnerId = user.getId();
		this.criteriaOwnerName = user.getName();
	}

	public void setCriteria(NotificationCriteria criteria)
	{
		this.criteriaId = criteria.getId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public String getOpportunityOwnerId() {
		return opportunityOwnerId;
	}

	public void setOpportunityOwnerId(String opportunityOwnerId) {
		this.opportunityOwnerId = opportunityOwnerId;
	}

	public String getCriteriaOwnerId() {
		return criteriaOwnerId;
	}

	public void setCriteriaOwnerId(String criteriaOwnerId) {
		this.criteriaOwnerId = criteriaOwnerId;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getOpportunityOwnerName() {
		return opportunityOwnerName;
	}

	public void setOpportunityOwnerName(String opportunityOwnerName) {
		this.opportunityOwnerName = opportunityOwnerName;
	}

	public String getCriteriaOwnerName() {
		return criteriaOwnerName;
	}

	public void setCriteriaOwnerName(String criteriaOwnerName) {
		this.criteriaOwnerName = criteriaOwnerName;
	}

	public Date getTimeEvaluated() {
		return timeEvaluated;
	}

	public void setTimeEvaluated(Date timeEvaluated) {
		this.timeEvaluated = timeEvaluated;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static String generateUniqueId(
			NotificationCriteria criteria,
			Opportunity opportunity,
			User recipient,
			User opportunityOwner,
			User criteriaOwner
			)
	{
		return String.format("%d-%s-%s-%s-%s",
				criteria.getId(),
				opportunity.getId(),
				recipient.getId(),
				opportunityOwner.getId(),
				criteriaOwner.getId()
				);
	}

	public void generateId() 
	{
		this.id = String.format("%d-%s-%s-%s-%s",
				criteriaId,
				opportunityId,
				recipientId,
				opportunityOwnerId,
				criteriaOwnerId
				);
	}
}
