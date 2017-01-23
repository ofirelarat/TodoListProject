package Exceptions;

/**
 * The class for presenting an exception in the connection to the DB
 * @author ofir & chen
 *
 */
public class ToDoListDaoException extends Exception
{
	private String message;
	private Exception superException;
	
	/**
	 * Constructor using fields
	 * @param message
	 * @param e
	 */
	public ToDoListDaoException(String message,Exception e)
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
	
	public Exception getSuperException()
	{
		return superException;
	}
	
	/**
	 * 
	 * @param superException
	 */
	
	private void setSuperException(Exception superException)
	{
		this.superException = superException;
	}
	
	
	
	
	
}
