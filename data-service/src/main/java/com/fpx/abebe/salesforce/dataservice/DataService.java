package com.fpx.abebe.salesforce.dataservice;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

import com.fpx.abebe.salesforce.SalesForceClientConnection;
import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.authentication.Credential;
import com.fpx.abebe.salesforce.database.DataAccess;
import com.fpx.abebe.salesforce.dataservice.task.AuthenticateServiceTask;
import com.fpx.abebe.salesforce.dataservice.task.EvaluatorServiceTask;
import com.fpx.abebe.salesforce.dataservice.task.InitializeDatabaseServiceTask;
import com.fpx.abebe.salesforce.dataservice.task.RegisterPushTopicServiceTask;
import com.fpx.abebe.salesforce.dataservice.task.StreamingClientServiceTask;
import com.fpx.abebe.salesforce.model.OpportunityStreamingMessage;
import com.fpx.abebe.salesforce.model.PushTopic;


public class DataService implements Runnable
{
	private final Logger logger = Logger.getLogger(DataService.class);
	private String propertyFileName;
	private Properties properties = new Properties(); 
	private Credential credential = new Credential();
	private SalesForceClientConnection clientConnection = new SalesForceClientConnection();
	private Access access = null;
	private DataAccess dataAccess = new DataAccess();
	private final String pushTopicName = "op.fpx.com";
	private PushTopic pushTopic = null;
	private boolean initializeDb = false;
	private StreamingClientServiceTask streamingTask = null;
	private BlockingQueue<OpportunityStreamingMessage> messageQueue = new ArrayBlockingQueue<OpportunityStreamingMessage>(64);
	private EvaluatorServiceTask evaluatorTask = new EvaluatorServiceTask(this,10);
	private Thread evaluatorThread = null;
	private int minutesToShutdown = 5;
	
	public String getPropertyFileName() {
		return propertyFileName;
	}
	public void setPropertyFileName(String propertyFileName) {
		this.propertyFileName = propertyFileName;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	public Credential getCredential() {
		return credential;
	}
	public void setCredential(Credential credential) {
		this.credential = credential;
	}
	public SalesForceClientConnection getClientConnection() {
		return clientConnection;
	}
	public void setClientConnection(SalesForceClientConnection clientConnection) {
		this.clientConnection = clientConnection;
	}
	public Access getAccess() {
		return access;
	}
	public void setAccess(Access access) {
		this.access = access;
	}
	public DataAccess getDataAccess() {
		return dataAccess;
	}
	public void setDataAccess(DataAccess dataAccess) {
		this.dataAccess = dataAccess;
	}
	public Logger getLogger() {
		return logger;
	}
	public String getPushTopicName() {
		return pushTopicName;
	}
	public PushTopic getPushTopic() {
		return pushTopic;
	}
	public void setPushTopic(PushTopic pushTopic) {
		this.pushTopic = pushTopic;
	}

	public boolean initialize() throws IOException
	{
		boolean status = true;
		FileInputStream inputStream = new FileInputStream(this.getPropertyFileName());
		this.properties.load(inputStream);
		this.credential.setUsingProperty(this.properties);
		return status;
	}

	private boolean runAuthenticateTask()
	{
		AuthenticateServiceTask task = new AuthenticateServiceTask(this);
		task.initialize();
		return task.execute();
	}

	private boolean runInitializeDbTask()
	{
		InitializeDatabaseServiceTask task = new InitializeDatabaseServiceTask(this);
		task.initialize();
		return task.execute();
	}

	private boolean runRegisterPushTopic()
	{
		RegisterPushTopicServiceTask task = new RegisterPushTopicServiceTask(this);
		task.initialize();
		return task.execute();
	}

	private boolean startStreamListenerTask()
	{
		this.setStreamingTask(new StreamingClientServiceTask(this));
		boolean status = this.getStreamingTask().initialize();
		status = status && this.getStreamingTask().execute();
		return status;
	}

	private boolean startEvaluatorTask()
	{		
		boolean status = this.getEvaluatorTask().initialize();
		if(status == true)
		{
			evaluatorThread = new Thread(getEvaluatorTask(),"Evaluator");
			evaluatorThread.start();
		}
		return status;
	}

	public void run() 
	{
		if(this.runAuthenticateTask() == true)
		{
			boolean status = true;
			if(this.initializeDb)
				status = this.runInitializeDbTask();
			if(status)
				status = this.runRegisterPushTopic();
			if(status)
				this.startStreamListenerTask();
			if(status)
				this.startEvaluatorTask();
			try 
			{
				Thread.sleep(this.getMinutesToShutdown()*60*1000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public void cleanup()
	{
		if(this.getStreamingTask() != null)
			this.getStreamingTask().cleanup();
		if(this.evaluatorTask != null)
			this.evaluatorTask.setTerminateFlag(true);
		if(this.evaluatorThread != null)
		{
			try 
			{
				this.evaluatorThread.join();
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		this.getDataAccess().getSessionFactory().close();
	}

	public void setInitializeDb(boolean value) 
	{
		this.initializeDb  = value;
	}
	public StreamingClientServiceTask getStreamingTask() {
		return streamingTask;
	}
	private void setStreamingTask(StreamingClientServiceTask streamingTask) {
		this.streamingTask = streamingTask;
	}
	public BlockingQueue<OpportunityStreamingMessage> getMessageQueue() 
	{
		return messageQueue;
	}
	public EvaluatorServiceTask getEvaluatorTask() {
		return evaluatorTask;
	}
	public int getMinutesToShutdown() {
		return minutesToShutdown;
	}
	public void setMinutesToShutdown(int minutesToShutdown) {
		this.minutesToShutdown = minutesToShutdown;
	}
}
