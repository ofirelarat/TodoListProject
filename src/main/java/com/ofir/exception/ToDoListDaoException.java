package com.ofir.exception;

public class ToDoListDaoException extends Exception
{
	private String message;
	private Throwable superException;
	
	public ToDoListDaoException(String message,Throwable e)
	{
		super();
		setMessage(message);
		setSuperException(e);
	}
	
	public String getMessage()
	{
		return message;
	}
	
	private void setMessage(String message)
	{
		this.message = message;
	}
	
	public Throwable getSuperException()
	{
		return superException;
	}
	
	private void setSuperException(Throwable e)
	{
		this.superException = e;
	}
}
