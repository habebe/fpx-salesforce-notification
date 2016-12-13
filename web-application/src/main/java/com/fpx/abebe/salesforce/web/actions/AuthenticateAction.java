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
		System.out.println("LOGIN action app:" + application);
		if(application != null)
		{
			Access access = application.login(this.getInputEmail(),
					this.getInputPassword());
			System.out.println("Email:" + this.getInputPassword());
			System.out.println("Password:" + this.getInputEmail());
			System.out.println("LOGIN action access:" + access);
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
		System.out.println("LOGOUT action acess:" + this.getAccess());
		this.getSession().clear();
		System.out.println("LOGOUT action acess:" + this.getAccess());
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
