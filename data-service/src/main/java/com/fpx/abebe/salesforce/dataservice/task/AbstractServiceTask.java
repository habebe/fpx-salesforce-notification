package com.fpx.abebe.salesforce.dataservice.task;

import org.apache.log4j.Logger;

import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.database.DataAccess;
import com.fpx.abebe.salesforce.dataservice.DataService;

public abstract class AbstractServiceTask
{
	private DataService dataService = null;

	public boolean initialize() { return true; }
	public boolean cleanup() { return true; }
	public abstract boolean execute();
	public AbstractServiceTask(DataService dataService) 
	{
		this.dataService = dataService;
	}

	public DataService getDataService() {
		return dataService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}
	
	public Logger getLogger()
	{
		return dataService.getLogger();
	}
	
	public Access getAccess()
	{
		return dataService.getAccess();
	}
	
	public DataAccess getDataAccess()
	{
		return this.dataService.getDataAccess();
	}
}
