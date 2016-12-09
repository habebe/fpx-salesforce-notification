package com.fpx.abebe.salesforce.notification;

public class EvaluatorResult 
{
	private EvaluatorResultStatus status = EvaluatorResultStatus.Unknown;
	private String message = null;
	public EvaluatorResultStatus getStatus() {
		return status;
	}
	public void setStatus(EvaluatorResultStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "EvaluatorResult [status=" + status + ", message=" + message + "]";
	}
	
	
	
}
