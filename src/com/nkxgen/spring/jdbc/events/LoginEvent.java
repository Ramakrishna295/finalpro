package com.nkxgen.spring.jdbc.events;

public class LoginEvent {

	private String event;
	
	public LoginEvent(String event)
	{
		this.event=event;
	}
	
	public String getEvent()
	{
		return event;
	}
}
