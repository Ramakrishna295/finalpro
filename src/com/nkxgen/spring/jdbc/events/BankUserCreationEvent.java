package com.nkxgen.spring.jdbc.events;

public class BankUserCreationEvent 
{
	
	private String event;
	
	public BankUserCreationEvent(String event)
	{
		this.event=event;
	}
	
	public String getEvent()
	{
		return event;
	}
}
