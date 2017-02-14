package com.main.exceptions;

/**
 * The class for presenting an exception in the connection to the DB
 * @author ofir & chen
 *
 */
public class ToDoListDaoException extends Exception
{
	private String message;
	private Throwable superException;
	
	/**
	 * Constructor using fields
	 * @param message
	 * @param e
	 */
	public ToDoListDaoException(String message,Throwable e)
	{
		super();
		setMessage(message);
		setSuperException(e);
	}
	
	/**
	 * return message of the error exception
	 */
	public String getMessage()
	{
		return message;
	}
	
	/**
	 * 
	 * @param message
	 */
	private void setMessage(String message)
	{
		this.message = message;
	}
	
	/**
	 * 
	 * @return the exception 
	 */
	
	public Throwable getSuperException()
	{
		return superException;
	}
	
	/**
	 * 
	 * @param e
	 */
	
	private void setSuperException(Throwable e)
	{
		this.superException = e;
	}
	
	
	
	
	
}
