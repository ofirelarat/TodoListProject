package Model;

import org.hibernate.HibernateException;
import Exceptions.*;

/**
 * 
 * @author ofir & chen 
 *
 */
public interface IToDoListDAO
{
	// Users Table methods
	
	/**
	 * 
	 * @param temp
	 * @return
	 * @throws ToDoListDaoException
	 */
	public boolean addUser(User temp) throws ToDoListDaoException;
	
	/**
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws ToDoListDaoException
	 */
	public User findUser(String email,String password) throws ToDoListDaoException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ToDoListDaoException
	 */
	public User getUser(int id) throws ToDoListDaoException;
	
	/**
	 * 
	 * @param temp
	 * @throws ToDoListDaoException
	 */
	public void editUser(User temp) throws ToDoListDaoException;
	
	/**
	 * 
	 * @param id
	 * @throws ToDoListDaoException
	 */
	public void deleteUser(int id) throws ToDoListDaoException;
	
	// Items Table methods
	
	/**
	 * 
	 * @param temp
	 * @throws ToDoListDaoException
	 */
	public void addItem(Item temp) throws ToDoListDaoException;
	
	/**
	 * 
	 * @param temp
	 * @throws ToDoListDaoException
	 */
	public void editItem(Item temp) throws ToDoListDaoException;
	
	/**
	 * 
	 * @param id
	 * @throws ToDoListDaoException
	 */
	public void deleteItem(int id) throws ToDoListDaoException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ToDoListDaoException
	 */
	public Item findItem(int id) throws ToDoListDaoException;
	
	/**
	 * 
	 * @param user
	 * @return
	 * @throws ToDoListDaoException
	 */
	public Item[] getItemsOfUser(User user) throws ToDoListDaoException;
}
