package com.fpx.abebe.salesforce.dataservice.listener;

import java.io.IOException;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpx.abebe.salesforce.dataservice.task.StreamingClientServiceTask;
import com.fpx.abebe.salesforce.model.OpportunityStreamingMessage;


public class StreamingClientMessageListener extends AbstractStreamingListener
{
	public StreamingClientMessageListener(StreamingClientServiceTask serviceTask)
	{
		super(serviceTask);
	}

	public void onMessage(ClientSessionChannel channel, Message message) 
	{

		this.getServiceTask().getLogger().info(String.format("[StreamingClientMessageListener.json]:%s",
				message.getJSON()
				));
		try {
			ObjectMapper mapper = new ObjectMapper();
			OpportunityStreamingMessage streamingMessage;
			streamingMessage = mapper.readValue(
					message.getJSON(), 
					OpportunityStreamingMessage.class);
			this.getServiceTask().getLogger().info(String.format("[StreamingClientMessageListener.obj]:%s",
					streamingMessage
					));
			this.getServiceTask().getDataService().getMessageQueue().add(streamingMessage);

		} catch (JsonParseException e) {

			e.printStackTrace();
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}
}
