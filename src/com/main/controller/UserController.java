package com.main.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main.exceptions.ToDoListDaoException;
import com.main.model.HibernateToDoListDAO;
import com.main.model.IToDoListDAO;
import com.main.model.Item;
import com.main.model.User;
import com.main.model.Item.Status;

/**
 * The controller which manage the the HTTPservlet request and response and connecting between the view and the model.
 * @author chen & ofir
 *
 */

@WebServlet("/userController/*")
public class UserController extends HttpServlet
{
	private IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
	private static final long serialVersionUID = 1L;
	
	public UserController()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		String path = request.getPathInfo();
		RequestDispatcher dispatcher = null;
				
		switch (path) {
		//forwarding to register page
		case "/register":
			dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
			dispatcher.forward(request, response);
			
			break;
			
		//forwarding to home page and also sending the user's items in the request.
		case "/home":
		{
			User user = (User)request.getSession().getAttribute("logedUser");
			if(user != null)
			{
				try
				{
					Item[] items = DAO.getItemsOfUser(user);
					request.setAttribute("items", items);
				} catch (ToDoListDaoException e)
				{
					e.printStackTrace();
				}
			}
			
		}
		dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
		dispatcher.forward(request, response);
		break;
		
		//forwarding to login page and terminate the session.
		case "/logout":
			request.getSession().invalidate();
			dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			
			break;
			
		//delete a specific item and updating the home page	
		case "/deleteItem":
			String id = request.getParameter("itemId");
			try
			{
				DAO.deleteItem(Integer.valueOf(id));
			} catch (ToDoListDaoException e)
			{
				e.printStackTrace();
				dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				dispatcher.forward(request, response);
			}
			try
			{
				request.setAttribute("items", DAO.getItemsOfUser((User)request.getSession().getAttribute("logedUser")));
			} catch (ToDoListDaoException e)
			{
				e.printStackTrace();
				dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				dispatcher.forward(request, response);
			}
			dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
			
			break;
			
		//forwarding to editing user page.
		case "/edituser":
			dispatcher = getServletContext().getRequestDispatcher("/edituser.jsp");
			dispatcher.forward(request, response);
			break;
		
		//default forwarding to login page.	
		default:
			dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String path = req.getPathInfo();
		RequestDispatcher dispatcher = null;
				
		String email;
		String password;
		User user;
		
		switch (path) {
		
			//Check if the user exits and forwarding to home page with the user items. 
			case "/login":
				email = req.getParameter("email");
			 	password = req.getParameter("password");
	
				try
				{
					//Admin page - shows the number of active sessions.
					if(email.equals("Admin@admin")&& password.equals("Admin"))
					{
						dispatcher = getServletContext().getRequestDispatcher("/adminpage.jsp");
						dispatcher.forward(req, resp);
					}
					user = DAO.findUser(email, password);
					if(user != null)
					{
						req.getSession().setAttribute("logedUser", user);
						Item[] items = DAO.getItemsOfUser(user);
						req.setAttribute("items", items);
						dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
						dispatcher.forward(req, resp);
						//resp.sendRedirect("home");
					}
					else{
						req.setAttribute("cantLog", true);
						dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
						dispatcher.forward(req, resp);
					}
					
				} catch (ToDoListDaoException e)
				{
					e.printStackTrace();
					dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
					dispatcher.forward(req, resp);
				}
			
				break;
				
			//Getting the parameters and adding a user. 
			case "/register":
				email = req.getParameter("email");
				password = req.getParameter("password");
				String firstName = req.getParameter("firstName");
				String lastName = req.getParameter("lastName");
				user = new User(email,firstName,lastName,password);
				
				try{
					
				if(DAO.addUser(user))
				{
					dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
					dispatcher.forward(req, resp);
				}
				
				}catch (ToDoListDaoException e) {
					e.printStackTrace();
					dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
					dispatcher.forward(req, resp);
				}
				
				break;
			
			//Getting parameters and adding a user item.
			case "/addItem":
				String title = req.getParameter("title");
				String content = req.getParameter("content");
				user = (User)req.getSession().getAttribute("logedUser");
				Item item = new Item(title,content,Item.Status.ready,user.getEmail());
				try
				{
					DAO.addItem(item);
					Item[] items = DAO.getItemsOfUser(user);
					req.setAttribute("items", items);
				} catch (ToDoListDaoException e)
				{
					e.printStackTrace();
					dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
					dispatcher.forward(req, resp);
				}
				
				dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
				dispatcher.forward(req, resp);
				break;
			
			//Getting parameters and editing the item.
			case "/editItem":
				int itemId = Integer.valueOf(req.getParameter("id"));
				title = req.getParameter("title");
				content = req.getParameter("content");
				Status status =Status.valueOf(req.getParameter("status"));
				
				user = (User)req.getSession().getAttribute("logedUser");
				
				try
				{					
					item =  DAO.findItem(itemId);
					item.setTitle(title);
					item.setContent(content);
					item.setStatus(status);
					DAO.editItem(item);
					
					Item[] items = DAO.getItemsOfUser(user);
					req.setAttribute("items", items);
				} catch (ToDoListDaoException e)
				{
					e.printStackTrace();
					dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
					dispatcher.forward(req, resp);
				}
				
				dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
				dispatcher.forward(req, resp);
				break;
				
				//Getting the parameters and editing the user.
				case "/editUser":
					firstName = req.getParameter("firstName");
					lastName = req.getParameter("lastName");
					password = req.getParameter("password");
					user = (User) req.getSession().getAttribute("logedUser");
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setPassword(password);
					try
					{
						DAO.editUser(user);
					} catch (ToDoListDaoException e)
					{
						e.printStackTrace();
						dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
						dispatcher.forward(req, resp);
					} 
					
					req.getSession().invalidate();
					dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
					dispatcher.forward(req, resp);
					
					break;
				
			default:
			super.doPut(req, resp);
		}
	}	
}
