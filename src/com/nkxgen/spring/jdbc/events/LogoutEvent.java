package com.nkxgen.spring.jdbc.events;

public class LogoutEvent 
{
	private String event;
	
	public LogoutEvent(String event)
	{
		this.event=event;
	}
	
	public String getEvent()
	{
		return event;
	}
}
