package com.fpx.abebe.salesforce.web.actions;

import com.fpx.abebe.salesforce.authentication.Access;
import com.fpx.abebe.salesforce.web.application.Application;
import com.opensymphony.xwork2.ActionSupport;

public class AuthenticateAction extends AbstractAction
{
	private static final long serialVersionUID = 1L;
	private String inputEmail;
	private String inputPassword;
	
	public String login()
	{
		
		Application application = this.getApplication();
		if(application != null)
		{
			Access access = application.login(this.getInputEmail(),
					this.getInputPassword());
			if(access != null)
			{
				this.saveAccess(access);
				return ActionSupport.SUCCESS;
			}
		}
		return ActionSupport.LOGIN;
	}

	public String logout()
	{
		this.getSession().clear();
		return ActionSupport.LOGIN; 
	}
	
	public String getInputEmail() {
		return inputEmail;
	}

	public void setInputEmail(String inputEmail) {
		this.inputEmail = inputEmail;
	}

	public String getInputPassword() {
		return inputPassword;
	}

	public void setInputPassword(String inputPassword) {
		this.inputPassword = inputPassword;
	}
}
