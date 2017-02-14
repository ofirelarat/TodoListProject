package com.main.controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class ServletListenerCounter implements HttpSessionListener
{
	private static int sessionCounter = 0;
	
	public static int getSessionCounter(){
		return sessionCounter;
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0)
	{
		sessionCounter++;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0)
	{
		sessionCounter--;
	}
	
}
