package com.fpx.abebe.salesforce.dataservice.listener;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;

import com.fpx.abebe.salesforce.dataservice.task.StreamingClientServiceTask;


public class StreamingClientStatusListener extends AbstractStreamingListener
{
	private String status = null;
	public StreamingClientStatusListener(StreamingClientServiceTask serviceTask,String status)
	{
		super(serviceTask);
	}

	public void onMessage(ClientSessionChannel channel, Message message) 
	{
	
		getServiceTask().getLogger().info(String.format("[StreamingClientMessageListener:%s]:%s",
				status,
				message.toString()
				));
	
		this.setSuccessful(message.isSuccessful());
		if (this.isSuccessful() == false) 
		{
			this.setError((String)message.get("error"));
			if (this.getError() != null) {
				getServiceTask().getLogger().error("Error during " + this.status + " " + this.getError());
			}
			this.setException((Exception) message.get("exception"));
			if (this.getException() != null) 
			{
				getServiceTask().getLogger().error("Exception during " + this.status,this.getException());
				this.getException().printStackTrace();
			}
		}
	}
	

}
