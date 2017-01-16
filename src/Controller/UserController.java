package Controller;

import java.io.IOException;

import javax.persistence.EnumType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exceptions.ToDoListDaoException;
import Model.HibernateToDoListDAO;
import Model.IToDoListDAO;
import Model.Item;
import Model.Item.Status;
import Model.User;


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
		case "/register":
			dispatcher = getServletContext().getRequestDispatcher("/Register.jsp");
			dispatcher.forward(request, response);
			
			break;
	 
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		dispatcher = getServletContext().getRequestDispatcher("/Home.jsp");
		dispatcher.forward(request, response);
		break;
		case "/logout":
			request.getSession().invalidate();
			dispatcher = getServletContext().getRequestDispatcher("/LogIn.jsp");
			dispatcher.forward(request, response);
			
			break;
			
		case "/deleteItem":
			String id = request.getParameter("itemId");
			try
			{
				DAO.deleteItem(Integer.valueOf(id));
			} catch (ToDoListDaoException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try
			{
				request.setAttribute("items", DAO.getItemsOfUser((User)request.getSession().getAttribute("logedUser")));
			} catch (ToDoListDaoException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispatcher = getServletContext().getRequestDispatcher("/Home.jsp");
			dispatcher.forward(request, response);
			
			break;
			
		case "/editUser":
			dispatcher = getServletContext().getRequestDispatcher("/EditUser.jsp");
			dispatcher.forward(request, response);
			break;
			
		default:
			dispatcher = getServletContext().getRequestDispatcher("/LogIn.jsp");
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
		
			case "/login":
				email = req.getParameter("email");
			 	password = req.getParameter("password");
	
				try
				{
					user = DAO.findUser(email, password);
					if(user != null)
					{
						req.getSession().setAttribute("logedUser", user);
						Item[] items = DAO.getItemsOfUser(user);
						req.setAttribute("items", items);
						dispatcher = getServletContext().getRequestDispatcher("/Home.jsp");
						dispatcher.forward(req, resp);
						//resp.sendRedirect("home");
					}
					else{
						dispatcher = getServletContext().getRequestDispatcher("/LogIn.jsp");
						dispatcher.forward(req, resp);
					}
					
				} catch (ToDoListDaoException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				break;
	
			case "/register":
				email = req.getParameter("email");
				password = req.getParameter("password");
				String firstName = req.getParameter("firstName");
				String lastName = req.getParameter("lastName");
				user = new User(email,firstName,lastName,password);
				
				try{
					
				if(DAO.addUser(user))
				{
					dispatcher = getServletContext().getRequestDispatcher("/LogIn.jsp");
					dispatcher.forward(req, resp);
				}
				
				}catch (ToDoListDaoException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				break;
				
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				dispatcher = getServletContext().getRequestDispatcher("/Home.jsp");
				dispatcher.forward(req, resp);
				break;
				
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				dispatcher = getServletContext().getRequestDispatcher("/Home.jsp");
				dispatcher.forward(req, resp);
				break;
				
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					req.getSession().invalidate();
					dispatcher = getServletContext().getRequestDispatcher("/LogIn.jsp");
					dispatcher.forward(req, resp);
					
					break;
				
			default:
			super.doPut(req, resp);
		}
	}	
}
