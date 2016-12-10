package com.fpx.abebe.salesforce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingMessage<T> extends SalesForceObject
{
	private String channel;
	private String clientId;
	private StreamingMessageData<T> data;
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public StreamingMessageData<T> getData() {
		return data;
	}
	public void setData(StreamingMessageData<T> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "StreamingMessage [channel=" + channel + ", clientId=" + clientId + ", data=" + data + "]";
	}
	
	
}
