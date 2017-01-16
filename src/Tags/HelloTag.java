package Tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import Model.Item.Status;

public class HelloTag extends SimpleTagSupport
{
	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	

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
