package com.ofir.database;

import org.hibernate.HibernateException;

import com.ofir.exception.*;
import com.ofir.model.Item;
import com.ofir.model.User;

public interface IToDoListDAO
{
	// Users Table methods

	public boolean addUser(User temp) throws ToDoListDaoException;
	
	public User findUser(String email,String password) throws ToDoListDaoException;
	
	public User getUser(int id) throws ToDoListDaoException;

	public void editUser(User temp) throws ToDoListDaoException;
	
	public void deleteUser(int id) throws ToDoListDaoException;
	
	// Items Table methods
	
	public void addItem(Item temp) throws ToDoListDaoException;
	
	public void editItem(Item temp) throws ToDoListDaoException;

	public void deleteItem(int id) throws ToDoListDaoException;
	
	public Item findItem(int id) throws ToDoListDaoException;
	
	public Item[] getItemsOfUser(User user) throws ToDoListDaoException;
}
