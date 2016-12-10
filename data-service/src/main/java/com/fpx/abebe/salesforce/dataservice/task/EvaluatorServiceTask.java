package com.fpx.abebe.salesforce.dataservice.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Session;

import com.fpx.abebe.salesforce.database.DataAccess;
import com.fpx.abebe.salesforce.database.NotificationCriteriaCollection;
import com.fpx.abebe.salesforce.dataservice.DataService;
import com.fpx.abebe.salesforce.model.NotificationCriteria;
import com.fpx.abebe.salesforce.model.NotificationMessage;
import com.fpx.abebe.salesforce.model.Opportunity;
import com.fpx.abebe.salesforce.model.OpportunityStreamingMessage;
import com.fpx.abebe.salesforce.model.User;
import com.fpx.abebe.salesforce.notification.EvaluatorResult;
import com.fpx.abebe.salesforce.notification.EvaluatorResultStatus;
import com.fpx.abebe.salesforce.notification.JavaScriptCriteriaEvaluator;
import com.fpx.abebe.salesforce.notification.JavaScriptEvaluator;

public class EvaluatorServiceTask extends RunnableServiceTask
{

	private List<NotificationMessage> notificationMessages = new ArrayList<NotificationMessage>();
	private JavaScriptEvaluator evaluator = new JavaScriptEvaluator();
	private int pollSeconds;
	private boolean terminateFlag = false;
	public EvaluatorServiceTask(DataService dataService,int pollSeconds) 
	{
		super(dataService);
		this.setPollSeconds(pollSeconds);
	}

	public boolean evaluate() 
	{
		this.getLogger().info(getClass() + "evaluate");
		notificationMessages.clear();
		DataAccess dataAccess = this.getDataAccess();
		Session session = dataAccess.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<User> users = dataAccess.queryUsers(session);
		Date date = new Date();
		for(User user:users)
		{
			NotificationCriteriaCollection collection = dataAccess.queryNotificationCriteriaForUser(user, 
					session);
			List<NotificationCriteria> criteria = collection.getResult();
			List<Opportunity> opportunities = dataAccess.queryOpportunityForUser(user,session);
			this.evaluateForUser(session,user,criteria,opportunities,date); 
		}
		session.getTransaction().commit();
		return this.submitNotificationMessages();
	}

	private boolean submitNotificationMessages() 
	{
		boolean status = true;
		if(this.notificationMessages.isEmpty() == false)
		{
			DataAccess dataAccess = this.getDataAccess();
			Session session = dataAccess.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			for(NotificationMessage message:notificationMessages)
			{
				session.saveOrUpdate(message);
			}
			session.getTransaction().commit();
		}
		return status;
	}

	private boolean evaluateForUser(
			Session session,
			User user, 
			List<NotificationCriteria> criteria, 
			List<Opportunity> opportunities,
			Date date
			) 
	{
		for(NotificationCriteria c:criteria)
		{
			JavaScriptCriteriaEvaluator cEvaluator = new JavaScriptCriteriaEvaluator(c,evaluator);
			for(Opportunity opportunity:opportunities)
			{
				EvaluatorResult result = cEvaluator.evaluate(opportunity, user);
				if((result.getStatus() == EvaluatorResultStatus.Match) || 
						(result.getStatus() == EvaluatorResultStatus.ErrorInExpressionEvaluation))
				{
					NotificationMessage message = new NotificationMessage();
					message.setCriteria(c);
					message.setOpportunity(opportunity);
					message.setOwner(user);
					message.setUser(user);
					if(result.getMessage() != null)
						message.setMessage(result.getMessage());
					message.setTimeEvaluated(date);
					message.generateId();
					notificationMessages.add(message);
					this.generateNotificationMessagesForManagers(session,user,user,c,
							opportunity,result,date);
				}
			}
		}
		return true;
	}

	private void generateNotificationMessagesForManagers(
			Session session,
			User owner,
			User user,
			NotificationCriteria criteria,
			Opportunity opportunity,
			EvaluatorResult result,
			Date date
			)
	{
		String managerId = user.getManagerId();
		if(managerId != null)
		{
			User manager = this.getDataAccess().queryUser(managerId, session);
			NotificationMessage message = new NotificationMessage();
			message.setCriteria(criteria);
			message.setOpportunity(opportunity);
			message.setOwner(owner);
			if(result.getMessage() != null)
				message.setMessage(result.getMessage());
			message.setUser(user);
			message.setTimeEvaluated(date);
			message.generateId();
			notificationMessages.add(message);
			this.generateNotificationMessagesForManagers(session,owner,manager,criteria,
					opportunity,result,date);

		}
	}

	public void run() 
	{
		while(this.isTerminateFlag() == false)
		{
			try {
				OpportunityStreamingMessage streamingMessage = getDataService().getMessageQueue().poll(
						this.getPollSeconds(),
						TimeUnit.SECONDS
						);
				getLogger().info(getClass().getName() + " message: " + streamingMessage);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			this.evaluate();
		}
	}

	@Override
	public boolean execute() 
	{
		return this.evaluate();
	}

	public int getPollSeconds() {
		return pollSeconds;
	}

	public void setPollSeconds(int pollSeconds) {
		this.pollSeconds = pollSeconds;
	}

	public boolean isTerminateFlag() {
		return terminateFlag;
	}

	public void setTerminateFlag(boolean terminateFlag) {
		this.terminateFlag = terminateFlag;
	}

}
