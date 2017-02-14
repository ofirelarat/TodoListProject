package com.main.model;

/**
 * 
 * @author ofir & chen
 * This class holds the ITEM parameters and his methods.
 */
public class Item
{
	private int id;
	private String userEmail;
	private String title;
	private String content;
	private Status status;
	
	/**
	 * 
	 * enum that describes the status of an item
	 *
	 */
    public enum Status
	{
		ready,
		inProgress,
		finish;
	}
    
    /**
     * default Constructor
     */
    public Item()
    {}
    
    /**
     * Constructor using fields
     * @param title
     * @param content
     * @param status
     * @param userMail
     */
	public Item(String title, String content, Status status, String userMail)
	{
		super();
		setTitle(title);
		setContent(content);
		setStatus(status);
		setUserEmail(userMail);
	}
	
	/**
	 * Constructor using fields
	 * @param id
	 * @param title
	 * @param content
	 * @param status
	 * @param userMail
	 */
	public Item(int id,String title, String content, Status status, String userMail)
	{
		super();
    	setId(id);
		setTitle(title);
		setContent(content);
		setStatus(status);
		setUserEmail(userMail);
	}
	
	/**
	 * 
	 * @return the ID of an item
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	
	/**
	 * 
	 * @return the user email that belongs to an item
	 */
	public String getUserEmail()
	{
		return userEmail;
	}
	
	/**
	 * 
	 * @param userEmail
	 */
	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}
	
	/**
	 * 
	 * @return the title of an item
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	/**
	 * 
	 * @return get the content of an item
	 */
	public String getContent()
	{
		return content;
	}
	
	/**
	 * 
	 * @param content
	 */
	public void setContent(String content)
	{
		this.content = content;
	}
	
	/**
	 * 
	 * @return status of an item
	 */
	public Status getStatus()
	{
		return status;
	}
	
	/**
	 * 
	 * @param status
	 */
	public void setStatus(Status status)
	{
		this.status = status;
	}
	
	/**
	 * to string method
	 */
	@Override
	public String toString()
	{
		return "Item [id=" + id + ", title=" + title + ", content=" + content + ", status=" + status + "]";
	}
}
