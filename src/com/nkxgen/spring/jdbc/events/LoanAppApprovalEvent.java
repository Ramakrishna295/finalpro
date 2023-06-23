package com.nkxgen.spring.jdbc.events;

public class LoanAppApprovalEvent 
{
	
	private String event;
	
	public LoanAppApprovalEvent(String event)
	{
		this.event=event;
	}
	
	public String getEvent()
	{
		return event;
	}
}
