package com.fpx.abebe.salesforce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingMessageData <T> extends SalesForceObject
{
	private StreamingMessageEvent event;
	private T sobject;
	public StreamingMessageEvent getEvent() {
		return event;
	}
	public void setEvent(StreamingMessageEvent event) {
		this.event = event;
	}
	public T getSobject() {
		return sobject;
	}
	public void setSobject(T sobject) {
		this.sobject = sobject;
	}
	@Override
	public String toString() {
		return "StreamingMessageData [event=" + event + ", sobject=" + sobject + "]";
	}
}
