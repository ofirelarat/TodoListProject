package Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import Exceptions.ToDoListDaoException;
import Model.HibernateToDoListDAO;
import Model.IToDoListDAO;
import Model.User;

@Path("/user")
public class RESTController
{
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
