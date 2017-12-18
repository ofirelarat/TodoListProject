package com.ofir.controllers.rest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ofir.database.UserHibernateDAO;
import com.ofir.exception.ToDoListDaoException;
import com.ofir.model.User;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@RequestMapping(method=RequestMethod.GET)
	public User[] getAllUsers(){
		UserHibernateDAO DAO = new UserHibernateDAO();
		List<User> users = Collections.emptyList();
		
		users = DAO.readAll();
		
		User[] usersArray = new User[users.size()];
		usersArray = users.toArray(usersArray);
		
		return usersArray;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public User getUserById(@PathVariable int id){
		UserHibernateDAO DAO = new UserHibernateDAO();
		try {
			Optional<User> optionalUser = DAO.read(id);
			if(optionalUser.isPresent()){
				return optionalUser.get();
			}
		} catch (ToDoListDaoException e) {
			e.printStackTrace();
		}
		
		throw new NullPointerException("user not found");
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void addUser(@RequestBody User user){
		UserHibernateDAO DAO = new UserHibernateDAO();
		try {
			 DAO.create(user);
		} catch (ToDoListDaoException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(method=RequestMethod.PATCH)
	public void editUser(@RequestBody User user){
		UserHibernateDAO DAO = new UserHibernateDAO();
		try {
			DAO.update(user);
		} catch (ToDoListDaoException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable int id){
		UserHibernateDAO DAO = new UserHibernateDAO();
		try {
			Optional<User> user = DAO.read(id);
			if(user.isPresent()){
				DAO.delete(user.get());
			}
			else{
				throw new NullPointerException("user not found");
			}
		} catch (ToDoListDaoException e) {
			e.printStackTrace();
		}
	}
}
