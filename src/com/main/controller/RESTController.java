package com.main.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.main.exceptions.ToDoListDaoException;
import com.main.model.HibernateToDoListDAO;
import com.main.model.IToDoListDAO;
import com.main.model.User;
/**
 * 
 * @author chen & ofir
 * RESTFUL controller with jersey framework
 */
@Path("/user")
public class RESTController
{
	/**
	 * hello method
	 * @return string "hello"
	 */
	@GET
	@Produces("text/plain")
	public String hello(){
		return "hello";
	}
	
	/**
	 * 
	 * @param id
	 * @return the user by his ID
	 */
	@Path("/{userid}")
	@GET
	@Produces("text/plain")
	public String getUser(@PathParam("userid")int id)
	{
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		User user = null;
		try
		{
			user = DAO.getUser(id);
		} catch (ToDoListDaoException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user.toString();
	}
}
