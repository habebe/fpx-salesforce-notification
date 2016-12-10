package com.fpx.abebe.salesforce.dataservice.task;

import com.fpx.abebe.salesforce.dataservice.DataService;

public abstract class RunnableServiceTask extends AbstractServiceTask implements Runnable 
{
	public RunnableServiceTask(DataService dataService) 
	{
		super(dataService);
	}
}
