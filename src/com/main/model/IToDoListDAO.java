package com.main.model;

import org.hibernate.HibernateException;

import com.main.exceptions.*;

/**
 * 
 * @author ofir & chen 
 *
 */
public interface IToDoListDAO
{
	// Users Table methods
	
	/**
	 * The method add a user to the DB
	 * @param temp
	 * @return true/false according to the add method
	 * @throws ToDoListDaoException
	 */
	public boolean addUser(User temp) throws ToDoListDaoException;
	
	/**
	 * The method find user by email and password - for login
	 * @param email
	 * @param password
	 * @return user 
	 * @throws ToDoListDaoException
	 */
	public User findUser(String email,String password) throws ToDoListDaoException;
	
	/**
	 * The method find user by the id of the user in the DB
	 * @param id
	 * @return user
	 * @throws ToDoListDaoException
	 */
	public User getUser(int id) throws ToDoListDaoException;
	
	/**
	 * The method for editing user
	 * @param temp
	 * @throws ToDoListDaoException
	 */
	public void editUser(User temp) throws ToDoListDaoException;
	
	/**
	 * The method for deleting user from the DB by ID
	 * @param id
	 * @throws ToDoListDaoException
	 */
	public void deleteUser(int id) throws ToDoListDaoException;
	
	// Items Table methods
	
	/**
	 * The method that adds item to the DB
	 * @param temp
	 * @throws ToDoListDaoException
	 */
	public void addItem(Item temp) throws ToDoListDaoException;
	
	/**
	 * The method that lets the user edit an item in his list
	 * @param temp
	 * @throws ToDoListDaoException
	 */
	public void editItem(Item temp) throws ToDoListDaoException;
	
	/**
	 * The method for deleting an item from the DB by its ID
	 * @param id
	 * @throws ToDoListDaoException
	 */
	public void deleteItem(int id) throws ToDoListDaoException;
	
	/**
	 * The method to find item  by ID
	 * @param id
	 * @return item
	 * @throws ToDoListDaoException
	 */
	public Item findItem(int id) throws ToDoListDaoException;
	
	/**
	 * Getting all the items of a specific user
	 * @param user
	 * @return array of items
	 * @throws ToDoListDaoException
	 */
	public Item[] getItemsOfUser(User user) throws ToDoListDaoException;
}
