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
	private String name;
	private String expression;
	private String owner;
	private String ownerId;
	private boolean enabled;

	public NotificationCriteria()
	{
	}

	public NotificationCriteria(String name,String expression, User owner,boolean enabled) 
	{
		this.name = name;
		this.expression = expression;
		this.setOwner(owner.getName());
		this.ownerId = owner.getId();
		this.enabled = enabled;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}
