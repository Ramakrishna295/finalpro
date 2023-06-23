package com.nkxgen.spring.jdbc.events;

public class AccountAppApprovalEvent 
{
	
	private String event;
	
	public AccountAppApprovalEvent(String event)
	{
		this.event=event;
	}
	
	public String getEvent()
	{
		return event;
	}
}
