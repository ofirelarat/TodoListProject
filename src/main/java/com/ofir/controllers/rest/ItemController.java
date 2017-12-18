package com.ofir.controllers.rest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ofir.database.HibernateToDoListDAO;
import com.ofir.database.IToDoListDAO;
import com.ofir.database.ItemHibernateDAO;
import com.ofir.database.UserHibernateDAO;
import com.ofir.exception.ToDoListDaoException;
import com.ofir.model.Item;
import com.ofir.model.User;

@RestController
@RequestMapping("/api/item")
public class ItemController {

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Item getItem(@PathVariable int id) throws ToDoListDaoException{
		ItemHibernateDAO DAO = new ItemHibernateDAO();
		
			Optional<Item> optionalItem = DAO.read(id);
			if(optionalItem.isPresent()){
				return optionalItem.get();
			}
		
		throw new NullPointerException("item not found");
	}

	@RequestMapping(method=RequestMethod.GET)
	public Item[] getItems(@RequestParam("userID") int userId) throws ToDoListDaoException{
		ItemHibernateDAO DAO = new ItemHibernateDAO();
		UserHibernateDAO userDAO = new UserHibernateDAO();
		
		Optional<User> user = userDAO.read(userId);
		if(user.isPresent()){
			List<Item> items = DAO.getUserItems(user.get());
		
			Item[] itemsArray = new Item[items.size()];
			itemsArray = items.toArray(itemsArray);
		
			return itemsArray;
		}else{
			throw new NullPointerException("user not found");
		}
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void addItem(@RequestBody Item item) throws ToDoListDaoException{
		ItemHibernateDAO DAO = new ItemHibernateDAO();
		
		DAO.create(item);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void deleteItem(@PathVariable int id) throws ToDoListDaoException{
		ItemHibernateDAO DAO = new ItemHibernateDAO();
		Optional<Item> optionalItem = DAO.read(id);
		if(optionalItem.isPresent()){
			DAO.delete(optionalItem.get());
		}
		else{
			throw new NullPointerException("item not found");
		}
	}
	
	@RequestMapping(method=RequestMethod.PATCH)
	public void editItem(@RequestBody Item item) throws ToDoListDaoException{
		ItemHibernateDAO DAO = new ItemHibernateDAO();

		DAO.update(item);
	}
}
