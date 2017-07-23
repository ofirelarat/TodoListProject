package com.ofir.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ofir.database.HibernateToDoListDAO;
import com.ofir.database.IToDoListDAO;
import com.ofir.exception.ToDoListDaoException;
import com.ofir.model.User;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@RequestMapping(method=RequestMethod.GET)
	public User[] getAllUsers(){
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		try {
			return DAO.getAllUsers();
		} catch (ToDoListDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public User getUserById(@PathVariable int id){
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		User user = null;
		try {
			user = DAO.getUser(id);
		} catch (ToDoListDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public boolean addUser(@PathVariable int id,@RequestBody User user){
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		try {
			user.setId(id);
			return DAO.addUser(user);
		} catch (ToDoListDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PATCH)
	public boolean editUser(@PathVariable int id,@RequestBody User user){
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		try {
			user.setId(id);
			DAO.editUser(user);
			return true;
		} catch (ToDoListDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable int id){
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		try {
			DAO.deleteUser(id);
		} catch (ToDoListDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
