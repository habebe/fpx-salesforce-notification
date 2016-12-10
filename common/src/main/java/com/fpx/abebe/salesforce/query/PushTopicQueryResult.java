package com.fpx.abebe.salesforce.query;

import com.fpx.abebe.salesforce.model.PushTopic;

public class PushTopicQueryResult extends QueryResult<PushTopic> 
{
	private final static String allPropertiesNames = 
			"ApiVersion,CreatedById,CreatedDate,Description,Id,IsActive,IsDeleted,"
					+ "LastModifiedById,LastModifiedDate,Name,NotifyForFields,"
					+ "NotifyForOperationCreate,NotifyForOperationDelete,NotifyForOperations,"
					+ "NotifyForOperationUndelete,NotifyForOperationUpdate,Query,SystemModstamp";

	private final static String objectName = "PushTopic";

	@Override
	public String getAllPropertiesNames() {
		return allPropertiesNames;
	}

	@Override	
	public String getObjectName() {
		return objectName;
	}	
}
