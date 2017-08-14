package com.ofir.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ofir.database.HibernateToDoListDAO;
import com.ofir.database.IToDoListDAO;
import com.ofir.exception.ToDoListDaoException;
import com.ofir.model.Item;
import com.ofir.model.User;

@Controller
@EnableWebMvc
public class MainController {
	
	@RequestMapping("/hello/{name}")
	@ResponseBody
	public String hello(@PathVariable String name){
		return "Hi " + name + "!";
	}
	
	@RequestMapping("/isLoggedFailed")
	@ResponseBody
	public boolean isLoggedFailed(HttpServletRequest request){
		Boolean loginFlag = (Boolean)request.getSession().getAttribute("loggedFailed");
		request.getSession().setAttribute("loggedFailed", null);
		
		if(loginFlag == true){
			return true;
		}
		
		return false;
	}
	
	@RequestMapping("/getLoggedUserName")
	@ResponseBody
	public String loggedUserName(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		if(user != null){
			return user.getFirstName() + " " + user.getLastName();
		}
		
		return null;
	}
	
	@RequestMapping("/login")
	public String loginPage(){
		return "pages/login.html";
	}
	
	@RequestMapping("/register")
	public String registerPage(){
		return "pages/register.html";
	}
		
	@RequestMapping("/itemsPage")
	public String itemsPage(HttpServletRequest request){
		if(request.getSession().getAttribute("user") != null){
			return "pages/items.html";
		}
		else{
			return "redirect:/login";
		}
	}
	
	@RequestMapping("/logout")
	public String logoutPage(HttpServletRequest request){
		request.getSession().setAttribute("user", null);
		return "redirect:/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginAction(HttpServletRequest request,HttpServletResponse response){
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		String email = request.getParameter("email");
		String password = request.getParameter("password");	
		String remember = request.getParameter("remember");
		try {
			User user = DAO.findUser(email, password);
			if(user != null){
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("loggedFailed", false);
				if(remember != null){
					response.addCookie(new Cookie("userId",String.valueOf(user.getId())));
				}
				
				return "redirect:/itemsPage";
			}
			else{
				request.getSession().setAttribute("loggedFailed", true);
				return "redirect:/login";
			}
		} catch (ToDoListDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "redirect:/open-page";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerAction(HttpServletRequest request){
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firstName= request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		User user = new User(email,firstName,lastName,password);
		try {
			if(DAO.addUser(user))
			{
				return "redirect:/login";
			}
		} catch (ToDoListDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/register";
	}
	
	@RequestMapping(value="/addItem", method=RequestMethod.POST)
	public String addItem(HttpServletRequest request){
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String status = request.getParameter("status");
		User user = (User)request.getSession().getAttribute("user");
		Item item = new Item(title,content,Item.Status.valueOf(status),user.getEmail());
		try {
			DAO.addItem(item);
		} catch (ToDoListDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/itemsPage";
	}
	
	@RequestMapping(value="/editItem", method=RequestMethod.POST)
	public String editItem(HttpServletRequest request){
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String status = request.getParameter("status");
		int id = Integer.valueOf(request.getParameter("itemId"));

		User user = (User)request.getSession().getAttribute("user");
		Item item = new Item(id,title,content,Item.Status.valueOf(status),user.getEmail());
		try {
			DAO.editItem(item);
		} catch (ToDoListDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/itemsPage";
	}
	
	@RequestMapping(value="/deleteItem", method=RequestMethod.POST)
	public String deleteItem(HttpServletRequest request){
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		int id = Integer.valueOf(request.getParameter("itemId"));
		try {
			DAO.deleteItem(id);
		} catch (ToDoListDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/itemsPage";
	}
	
	@RequestMapping("/items")
	@ResponseBody
	public String items(HttpServletRequest request){
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		User user = (User)request.getSession().getAttribute("user");
		StringBuilder builder = new StringBuilder();
		try {
			Item[] items = DAO.getItemsOfUser(user);
			for (Item item : items) {
				builder.append(item.toString() + "-");
			}
			builder.deleteCharAt(builder.length()-1);
			return builder.toString();
		} catch (ToDoListDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping("/*")
	public String openPage(){
		return  "pages/open-page.html";
	}
}
