package com.nkxgen.spring.jdbc.events;


public class LoanAppRequestEvent {
	
	private String event;
	
	public LoanAppRequestEvent(String event)
	{
		this.event=event;
	}
	
	public String getEvent()
	{
		return event;
	}

}
