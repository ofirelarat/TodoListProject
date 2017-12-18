package com.ofir.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ofir.database.HibernateToDoListDAO;
import com.ofir.database.IToDoListDAO;
import com.ofir.database.ItemHibernateDAO;
import com.ofir.database.UserHibernateDAO;
import com.ofir.exception.ToDoListDaoException;
import com.ofir.model.Item;
import com.ofir.model.User;

@Controller
@EnableWebMvc
public class MainController {
	
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
	
	@RequestMapping("/isRegisterFailed")
	@ResponseBody
	public boolean isRegisterFailed(HttpServletRequest request){
		Boolean loginFlag = (Boolean)request.getSession().getAttribute("registerFailed");
		request.getSession().setAttribute("registerFailed", null);
		
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
			return "redirect:/*";
		}
	}
	
	@RequestMapping("/logout")
	public String logoutPage(HttpServletRequest request,HttpServletResponse response){
		request.getSession().setAttribute("user", null);
		response.addCookie(new Cookie("userId", null));
		return "redirect:/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginAction(HttpServletRequest request,HttpServletResponse response){
		UserHibernateDAO DAO = new UserHibernateDAO();
		String email = request.getParameter("email");
		String password = request.getParameter("password");	
		String remember = request.getParameter("remember");
		try {
			Optional<User> user = DAO.loginUser(email, password);
			if(user.isPresent()){
				request.getSession().setAttribute("user", user.get());
				request.getSession().setAttribute("loggedFailed", false);
				if(remember != null){
					response.addCookie(new Cookie("userId",String.valueOf(user.get().getId())));
				}
				
				return "redirect:/itemsPage";
			}
			else{
				request.getSession().setAttribute("loggedFailed", true);
				return "redirect:/login";
			}
		} catch (ToDoListDaoException e) {
			e.printStackTrace();
		}
		
		return "redirect:/open-page";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerAction(HttpServletRequest request){
		UserHibernateDAO DAO = new UserHibernateDAO();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firstName= request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		User user = new User(email,firstName,lastName,password);
		try {
			if(user.xssProofing()){
				DAO.create(user);
				
				request.getSession().setAttribute("registerFailed", false);
				return "redirect:/login";
			}
		} catch (ToDoListDaoException e) {
			e.printStackTrace();
		}
		
		request.getSession().setAttribute("registerFailed", true);
		return "redirect:/register";
	}
	
	@RequestMapping(value="/addItem", method=RequestMethod.POST)
	public String addItem(HttpServletRequest request,@RequestParam("title") String title,@RequestParam("content") String content,@RequestParam("status")String status){
		ItemHibernateDAO DAO = new ItemHibernateDAO();
		User user = (User)request.getSession().getAttribute("user");
		Item item = new Item(title,content,Item.Status.valueOf(status),user.getEmail());
		try {
			if(item.xssProofing()){
				DAO.create(item);
			}
		} catch (ToDoListDaoException e) {
			e.printStackTrace();
		}
		
		return "redirect:/itemsPage";
	}
	
	@RequestMapping(value="/editItem", method=RequestMethod.POST)
	@ResponseBody
	public boolean editItem(HttpServletRequest request,@RequestParam("title") String title,@RequestParam("content") String content,@RequestParam("status")String status,@RequestParam("id") int id){
		ItemHibernateDAO DAO = new ItemHibernateDAO();
		User user = (User)request.getSession().getAttribute("user");
		Item item = new Item(id,title,content,Item.Status.valueOf(status),user.getEmail());
		try {
			if(item.xssProofing()){
				DAO.update(item);
			}
			return true;
		} catch (ToDoListDaoException e) {
			e.printStackTrace();
			return  false;
		}
	}
	
	@RequestMapping("/items")
	@ResponseBody
	public String items(HttpServletRequest request){
		ItemHibernateDAO DAO = new ItemHibernateDAO();
		User user = (User)request.getSession().getAttribute("user");
		StringBuilder builder = new StringBuilder();
		
		List<Item> items = DAO.getUserItems(user);
		for (Item item : items) {
			builder.append(item.toString() + "-");
		}
		
		if(builder.length() > 0){
			builder.deleteCharAt(builder.length()-1);
		}
		
		return builder.toString();
	}
	
	@RequestMapping("/")
	public String openPage(HttpServletRequest request){
		if(request != null && request.getCookies() != null){
			for (Cookie coockie: request.getCookies()) {
				if(coockie.getName().equals("userId") && coockie.getValue() != null){
					UserHibernateDAO DAO = new UserHibernateDAO();
					try {
						Optional<User> user = DAO.read(Integer.valueOf(coockie.getValue()));
						if(user.isPresent()){
							request.getSession().setAttribute("user", user.get());
							return "redirect:/itemsPage";
						}
					} catch (NumberFormatException | ToDoListDaoException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return  "pages/open-page.html";
	}
}
