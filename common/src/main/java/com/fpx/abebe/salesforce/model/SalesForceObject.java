package com.fpx.abebe.salesforce.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public abstract class SalesForceObject 
{
	static DateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	static 
	{
		dateParser.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	public Date parseDateFromString(String value)
	{
		Date date = null;
		if(value != null)
		{
			try 
			{
				date = dateParser.parse(value);
			} 
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
}
