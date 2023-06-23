package com.nkxgen.spring.jdbc.events;

public class BankUserDetailsModificationEvent 
{
	
	private String event;
	
	public BankUserDetailsModificationEvent(String event)
	{
		this.event=event;
	}
	
	public String getEvent()
	{
		return event;
	}
}
