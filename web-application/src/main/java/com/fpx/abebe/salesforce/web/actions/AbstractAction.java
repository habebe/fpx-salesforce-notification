package com.fpx.abebe.salesforce.web.actions;

import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.web.application.Application;
import com.opensymphony.xwork2.ActionSupport;

public class AbstractAction  extends ActionSupport implements 
SessionAware,ServletContextAware
{
	private static final long serialVersionUID = 1L;
	private ServletContext servletContext = null;
	private Map<String, Object> session = null;
	
	protected Application getApplication()
	{
		Application application = Application.getInstance(getServletContext());
		return application;
	}
	
	public Access getAccess()
	{
		System.out.println("GET ACCESS " + (Access)this.session.get("access"));
		return (Access)this.session.get("access");
	}
	
	protected void saveAccess(Access access) 
	{
		this.session.put("access",access);
		System.out.println("SAVE ACCESS " + access);
	}
	
	public void setSession(Map<String, Object> map) 
	{
		this.session = map;	
	}
	
	public Map<String,Object> getSession()
	{
		return this.session;
	}
	
	public void setServletContext(ServletContext servletContext) 
	{
		System.out.println("SET SERVELET CONTEXT.");
		this.servletContext = servletContext;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}
	
	
	
}
