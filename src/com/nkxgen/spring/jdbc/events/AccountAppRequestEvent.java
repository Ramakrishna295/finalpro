package com.nkxgen.spring.jdbc.events;


public class AccountAppRequestEvent {
	
	private String event;
	
	public AccountAppRequestEvent(String event)
	{
		this.event=event;
	}
	
	public String getEvent()
	{
		return event;
	}

}
