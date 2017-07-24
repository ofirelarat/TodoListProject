package com.ofir.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ofir.database.HibernateToDoListDAO;
import com.ofir.database.IToDoListDAO;
import com.ofir.exception.ToDoListDaoException;
import com.ofir.model.Item;
import com.ofir.model.User;

@RestController
@RequestMapping("/api/item")
public class ItemController {

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Item getItem(@PathVariable int id){
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		try {
			return DAO.findItem(id);
		} catch (ToDoListDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Item[] userItems(@RequestParam(value = "userId",required=false) Integer id){
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		User user;
		try {
			if(id == null){
				return DAO.getAllItems();
			}
			else{
			user = DAO.getUser(id);
			return DAO.getItemsOfUser(user);
			}
		} catch (ToDoListDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void addItem(@RequestBody Item item){
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		try {
			DAO.addItem(item);
		} catch (ToDoListDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void deleteItem(@PathVariable int id){
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		try {
			DAO.deleteItem(id);
		} catch (ToDoListDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(method=RequestMethod.PATCH)
	public void editItem(@RequestBody Item item){
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		try {
			DAO.editItem(item);
		} catch (ToDoListDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
