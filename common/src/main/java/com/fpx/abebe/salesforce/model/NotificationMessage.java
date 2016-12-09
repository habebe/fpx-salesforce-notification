package com.fpx.abebe.salesforce.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NotificationMessage_Table")
public class NotificationMessage 
{
	@Id
	private String id;
	private User toUser;
	private NotificationCriteria criteria;
	private Date timeEvaluated;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getToUser() {
		return toUser;
	}
	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
	public NotificationCriteria getCriteria() {
		return criteria;
	}
	public void setCriteria(NotificationCriteria criteria) {
		this.criteria = criteria;
	}
	public Date getTimeEvaluated() {
		return timeEvaluated;
	}
	public void setTimeEvaluated(Date timeEvaluated) {
		this.timeEvaluated = timeEvaluated;
	}
	@Override
	public String toString() {
		return "NotificationMessage [id=" + id + ", toUser=" + toUser + ", criteria=" + criteria + ", timeEvaluated="
				+ timeEvaluated + "]";
	}	
}
