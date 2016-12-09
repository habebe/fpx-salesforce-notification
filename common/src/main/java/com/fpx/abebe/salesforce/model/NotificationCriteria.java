package com.fpx.abebe.salesforce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="NotificationCriteriaTable")
public class NotificationCriteria 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String expression;
	private User owner;

	public NotificationCriteria()
	{
	}

	public NotificationCriteria(String expression, User owner) 
	{
		this.expression = expression;
		this.owner = owner;
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
	public User getOwner() {
		return owner;
	}
	public void setOwnerId(User owner) {
		this.owner = owner;
	}
}
