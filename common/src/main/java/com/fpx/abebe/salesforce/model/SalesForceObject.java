package com.fpx.abebe.salesforce.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public abstract class SalesForceObject 
{
	static DateFormat timeParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	static DateFormat timeParserNoZone = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS");
	static DateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
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
				if(value.length() == 10)
					date = dateParser.parse(value);
				else if(value.length() == 24)
					date = timeParserNoZone.parse(value);
				else
					date = timeParser.parse(value);
			} 
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
}
