package com.fpx.abebe.salesforce.query;

import java.util.ArrayList;
import java.util.List;

import com.fpx.abebe.salesforce.model.SalesForceObject;

public abstract class QueryResult<T extends SalesForceObject>
{
	public abstract String getAllPropertiesNames();
	public abstract String getObjectName();
	
	private int totalSize;
	private boolean done;
	private List<T> records = new ArrayList<T>();
	
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	@Override
	public String toString() 
	{
		return "QueryResult [totalSize=" + totalSize + ", done=" + done + ", records=" + records + "]";
	}
	
	
}
