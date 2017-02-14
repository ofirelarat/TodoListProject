package com.view.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.main.model.Item.Status;
/**
 * Custom tag that prints "Hello" with the "name of user"
 * @author chen & ofir
 *
 */
public class HelloTag extends SimpleTagSupport
{
	private String name;
	
	/**
	 * 
	 * @return name of the user
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * 
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * The method that prints the string if the user is logged. Otherwise prints "you are not logged" string
	 */
	@Override
	public void doTag() throws JspException, IOException
	{
		if(name != null){
		getJspContext().getOut().print("Hello " + name + "!");
		}
		else{
			getJspContext().getOut().print("Hello,you are not logged yet");
		}
	}
	

}
