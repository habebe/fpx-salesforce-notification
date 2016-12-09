package com.fpx.abebe.salesforce.query;

import com.fpx.abebe.salesforce.model.User;

public class UserQueryResult extends QueryResult<User> 
{
	private final static String allPropertiesNames = 
			"AccountId,Address,Alias,City,"
			+ "CommunityNickname,CompanyName,ContactId,Country,CreatedById,CreatedDate,"
			+ "Department,"
			+ "Division,Email,FirstName,"
			+ "Id,IsActive,"
			+ "LastLoginDate,LastModifiedById,LastModifiedDate,LastName,"
			+ "LastViewedDate,Latitude,"
			+ "Longitude,ManagerId,MobilePhone,Name,"
			+ "Phone,PostalCode,ProfileId,"
			+ "State,"
			+ "Street,SystemModstamp,"
			+ "Title,Username,"
			+ "UserRoleId,UserType";
	private final static String objectName = "User";
	
	@Override
	public String getAllPropertiesNames() {
		return allPropertiesNames;
	}

	@Override	
	public String getObjectName() {
		return objectName;
	}	
}
