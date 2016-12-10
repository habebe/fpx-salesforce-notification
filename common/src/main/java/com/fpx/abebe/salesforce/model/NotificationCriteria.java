package com.fpx.abebe.salesforce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NotificationCriteriaTable")
public class NotificationCriteria
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String expression;
	private String ownerId;

	public NotificationCriteria()
	{
	}

	public NotificationCriteria(String expression, String ownerId) 
	{
		this.expression = expression;
		this.ownerId = ownerId;
	}

	public int getId() {
		return id;
	}
	
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
}
