package com.fpx.abebe.salesforce.web.interceptor;

import java.util.Map;

import com.fpx.abebe.salesforce.authentication.Access;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AccessCheckInterceptor  extends AbstractInterceptor 
{
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception 
	{
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		Access access = (Access) session.get("access");
		System.out.println("AccessCheckInterceptor.intercept access:"+access);
		if (access == null) 
		{
			return Action.ERROR;
		} 
		else 
		{
			return invocation.invoke();
		}
	}
}