package com.fpx.abebe.salesforce.dataservice.task;

import java.util.HashMap;
import java.util.Map;

import org.cometd.bayeux.Channel;
import org.cometd.client.BayeuxClient;
import org.cometd.client.transport.ClientTransport;
import org.cometd.client.transport.LongPollingTransport;
import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;

import com.fpx.abebe.salesforce.SalesForceURL;
import com.fpx.abebe.salesforce.dataservice.DataService;
import com.fpx.abebe.salesforce.dataservice.listener.StreamingClientMessageListener;
import com.fpx.abebe.salesforce.dataservice.listener.StreamingClientStatusListener;


public class StreamingClientServiceTask extends AbstractServiceTask
{
	private BayeuxClient bayeuxClient = null;
	private HttpClient httpClient = null;	
	private int connectionTimeout;
	private int readTimeout;

	public StreamingClientServiceTask(DataService dataService,
			int connectionTimeout,
			int readTimeout
			) 
	{
		super(dataService);
		this.connectionTimeout = connectionTimeout;
		this.readTimeout = readTimeout;
	}

	public StreamingClientServiceTask(DataService dataService) 
	{
		this(dataService,20000,120000);
	}
	
	@Override
	public boolean initialize()
	{
		getLogger().info(this.getClass() + ".initialize");
		boolean status = this.initializeBayeuxClient();
		if(status)
		{
			this.bayeuxClient.getChannel(Channel.META_HANDSHAKE).
			addListener(new StreamingClientStatusListener(this,"handShake"));

			this.bayeuxClient.getChannel(Channel.META_CONNECT).
			addListener(new StreamingClientStatusListener(this,"connect"));

			this.bayeuxClient.getChannel(Channel.META_SUBSCRIBE).
			addListener(new StreamingClientStatusListener(this,"subscribe"));

			bayeuxClient.handshake();
			getLogger().info("Waiting for handshake");
			boolean handshaken = this.bayeuxClient.waitFor(10 * 1000, BayeuxClient.State.CONNECTED);
			if (!handshaken) {
				getLogger().error("Failed to handshake: " + bayeuxClient);
				status = false;
			}
			getLogger().info("Subscribing to channel: " + this.getDataService().getPushTopicName());
			
			bayeuxClient.getChannel(SalesForceURL.getStreamingChannelUrl(
					this.getDataService().getPushTopicName())).
			subscribe(new StreamingClientMessageListener(this));
			
			
			
			/*new MessageListener() {
				public void onMessage(ClientSessionChannel channel, Message message) {
					getLogger().info("+++Received Message: " + message);
				}
			});*/
		}
		return status;
	}

	private boolean initializeBayeuxClient() 
	{
		boolean status = true;
		getLogger().info(this.getClass() + ".initializeBayeuxClient");
		try
		{
			this.httpClient = new HttpClient();
			httpClient.setConnectTimeout(this.connectionTimeout);
			httpClient.setTimeout(this.readTimeout);
			httpClient.start();

			Map<String, Object> options = new HashMap<String, Object>();
			options.put(ClientTransport.TIMEOUT_OPTION, this.readTimeout);
			final String token = this.getAccess().getToken();
			LongPollingTransport transport = new LongPollingTransport(
					options, httpClient) {

				@Override
				protected void customize(ContentExchange exchange) {
					super.customize(exchange);
					exchange.addRequestHeader("Authorization", "OAuth " + token);
				}
			};

			getLogger().info("Using streaming endpoint " + 
			SalesForceURL.getStreamingEndpoint(this.getAccess()));
			
			this.bayeuxClient = new BayeuxClient(
					SalesForceURL.getStreamingEndpoint(this.getAccess()),
					transport);
			this.establishCookies(this.bayeuxClient, 
					this.getDataService().getCredential().getUserName(),
					token);
		}
		catch (Exception e)
		{
			this.getLogger().error("Error initialzing streaming client.",e);
			status = false;
		}

		return status;
	}


	private void establishCookies(BayeuxClient client, String user, 
			String sid) {
		client.setCookie("com.salesforce.LocaleInfo", "us", 24 * 60 * 60 * 1000);
		client.setCookie("login", user, 24 * 60 * 60 * 1000);
		client.setCookie("sid", sid, 24 * 60 * 60 * 1000);
		client.setCookie("language", "en_US", 24 * 60 * 60 * 1000);
	}

	@Override
	public boolean execute() 
	{
		return true;
	}
	
	@Override
	public boolean cleanup()
	{
		bayeuxClient.disconnect();
		return bayeuxClient.waitFor(2000, BayeuxClient.State.DISCONNECTED);
	}
}
