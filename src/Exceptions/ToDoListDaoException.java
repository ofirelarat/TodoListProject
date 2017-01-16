package Exceptions;

/**
 *
 * @author ofir & chen
 *
 */
public class ToDoListDaoException extends Exception
{
	private String message;
	private Exception superException;
	
	/**
	 * 
	 * @param message
	 * @param e
	 */
	public ToDoListDaoException(String message,Exception e)
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
	public Exception getSuperException()
	{
		return superException;
	}
	private void setSuperException(Exception superException)
	{
		this.superException = superException;
	}
	
	
	
	
	
}
