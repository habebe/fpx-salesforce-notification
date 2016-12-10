package com.fpx.abebe.salesforce.dataservice.listener;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel.MessageListener;

import com.fpx.abebe.salesforce.dataservice.task.StreamingClientServiceTask;


public abstract class AbstractStreamingListener implements MessageListener
{
	private boolean successful = true;
	private String error = null;
	private Exception exception = null;
	private Message message = null;
	private StreamingClientServiceTask serviceTask;
	
	public AbstractStreamingListener(StreamingClientServiceTask serviceTask)
	{
		this.serviceTask = serviceTask;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public StreamingClientServiceTask getServiceTask() {
		return serviceTask;
	}
}
