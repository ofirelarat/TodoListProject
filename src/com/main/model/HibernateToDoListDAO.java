package com.main.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.main.exceptions.*;

/**
 * 
 * @author ofir & chen
 * The class is singeltone and implements the IToDoListDao using hibernate.
 */
public class HibernateToDoListDAO implements IToDoListDAO
{

	private static HibernateToDoListDAO instance;
	
	private SessionFactory factory;
			
	private HibernateToDoListDAO()
	{
		//creating factory for getting sessions
		Configuration configuration =  new AnnotationConfiguration();
		configuration.setProperty("javax.persistence.validation.mode","NONE");
		configuration.configure();
		factory = configuration.buildSessionFactory();
	}
	
	/**
	 * This method returns the instance of this class 
	 * @return - instance (HibernateToDoListDao)
	 */
	public static HibernateToDoListDAO getInstance()
	{
		if(instance == null)
		{
			instance = new HibernateToDoListDAO();
		}
		
		return instance;
	}
	
	@Override
	public boolean addUser(User temp) throws ToDoListDaoException
	{
		boolean flag = false;
		//Checking if the email already exists.
		if(!IsExistUser(temp.getEmail()))
		{
			Session session = factory.openSession();
			try{
				session.beginTransaction();
				session.save(temp);
				session.getTransaction().commit();
				flag = true;
			}
			catch (HibernateException e) {
				session.getTransaction().rollback();
				//e.printStackTrace();
				throw new ToDoListDaoException("Error. can not add user - " + e.getMessage(), e);
			}
			finally {
				session.close();
			}
		}
		
		return flag;
	}

	@Override
	public User findUser(String email, String password) throws ToDoListDaoException
	{
		Session session = factory.openSession();
		User user = null;
		try{
			session.beginTransaction();
			//User searching in DB by email and password
			String hql = String.format("FROM User U WHERE U.email = '%s' and U.password = '%s'",email,password);
			Query query = session.createQuery(hql);
			List users  =  query.list();
			session.getTransaction().commit();
			if(users.size() > 0)
			{
				user = (User) users.get(0);
			}
		}
		catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new ToDoListDaoException("Error. can not find user - " + e.getMessage(), e);
		}
		finally{
			session.close();
		}
			
		return user;
	}
	
	public User getUser(int id) throws ToDoListDaoException
	{
		Session session = factory.openSession();
		User user = null;
		try{
			session.beginTransaction();
			user = (User) session.get(User.class,id);
			
		}catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new ToDoListDaoException("Error. can not get user - " + e.getMessage(), e);
		}finally {
			session.close();
		}
		
		return user;
	}
	
	@Override
	public void editUser(User temp) throws ToDoListDaoException
	{
		Session session = factory.openSession();
		
		//Find user by the ID parameter and setting the other parameters.
		try{
			session.beginTransaction();
			User user = (User)session.get(User.class, temp.getId());
			user.setFirstName(temp.getFirstName());
			user.setLastName(temp.getLastName());
			user.setPassword(temp.getPassword());
			session.update(user);
			session.getTransaction().commit();
		}
		catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new ToDoListDaoException("Error. can not edit user - " + e.getMessage(), e);
		}
		finally{
			session.close();
		}
	}

	@Override
	public void deleteUser(int id) throws ToDoListDaoException
	{
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			User user = (User)session.get(User.class, id);
			session.delete(user);
			session.getTransaction().commit();
		}
		catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new ToDoListDaoException("Error. can not delete user - " + e.getMessage(), e);
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * This boolean method checks if the email is already in use.
	 * @param email
	 * @return
	 * @throws ToDoListDaoException
	 */
	private boolean IsExistUser(String email) throws ToDoListDaoException
	{
		Session session = factory.openSession();
		List users = null;
		try{
			session.beginTransaction();
			
			//Searching user by email.
			String hql = String.format("FROM User U WHERE U.email = '%s' ",email);
			Query query = session.createQuery(hql);
			users  =  query.list();
			session.getTransaction().commit();
		}
		catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new ToDoListDaoException("Error. can not find is user exist - " + e.getMessage(), e);
		}
		finally{
			session.close();
		}
		
		if(users.size() < 1)
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public void addItem(Item temp) throws ToDoListDaoException
	{
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			session.save(temp);
			session.getTransaction().commit();
		}
		catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new ToDoListDaoException("Error. can not add item - " + e.getMessage(), e);
		}
		finally {
			session.close();
		}
	}		


	@Override
	public void editItem(Item temp) throws ToDoListDaoException
	{
		Session session = factory.openSession();
		//Find item by the ID parameter and setting the other parameters.
		try{
			session.beginTransaction();
			Item item = (Item)session.get(Item.class, temp.getId());
			item.setTitle(temp.getTitle());
			item.setContent(temp.getContent());
			item.setStatus(temp.getStatus());
			session.update(item);
			session.getTransaction().commit();
		}
		catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new ToDoListDaoException("Error. can not edit item - " + e.getMessage(), e);
		}
		finally{
			session.close();
		}
		
	}

	@Override
	public void deleteItem(int id) throws ToDoListDaoException
	{
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			Item item = (Item)session.get(Item.class, id);
			session.delete(item);
			session.getTransaction().commit();
		}
		catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw new ToDoListDaoException("Error. can not delete edit item - " + e.getMessage(), e);
		}
		finally {
			session.close();
		}
		
	}

	@Override
	public Item findItem(int id) throws ToDoListDaoException
	{
		Item item = null;
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			item = (Item)session.get(Item.class, id);
			session.getTransaction().commit();
		}
		catch (HibernateException e) {
			session.getTransaction().rollback();
			//e.printStackTrace();
			throw new ToDoListDaoException("Error. can not delete find item - " + e.getMessage(), e);
		}
		finally{
			session.close();
		}
				
		return item;
	}

	@Override
	public Item[] getItemsOfUser(User user) throws ToDoListDaoException
	{
		Session session = factory.openSession();
		List items = null;
		try{
			session.beginTransaction();
			//Find the items of a specific user by the user email.
			String hql = String.format("FROM Item I WHERE I.userEmail = '%s'", user.getEmail());
			Query query = session.createQuery(hql);
			items =  query.list();
			session.getTransaction().commit();
		}
		catch (HibernateException e) {
			session.getTransaction().rollback(); 
			//e.printStackTrace();
			throw new ToDoListDaoException("Error. can not get all the current user's items. - " + e.getMessage(), e);
		}
		finally{
			session.close();
		}
		
		Item[] itemsArray = new Item[items.size()];
		items.toArray(itemsArray);
		return itemsArray;
	}

}
