package com.fpx.abebe.salesforce.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingMessageEvent extends SalesForceObject
{
	private String type;
	private int replayId;
	private Date createdDate;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getReplayId() {
		return replayId;
	}
	public void setReplayId(int replayId) {
		this.replayId = replayId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.setCreatedDate(this.parseDateFromString(createdDate));
	}
	@JsonIgnore
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public String toString() {
		return "StreamingMessageEvent [type=" + type + ", replayId=" + replayId + ", createdDate=" + createdDate + "]";
	}
	
}
