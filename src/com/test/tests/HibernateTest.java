package com.test.tests;

import org.apache.log4j.DailyRollingFileAppender;
import org.junit.Test;

import com.main.exceptions.ToDoListDaoException;
import com.main.model.*;
import com.main.model.Item.Status;

/**
 * Class Test that checking the hibernateToDoListDAO 
 * @author chen & ofir
 *
 */
public class HibernateTest
{
	/**
	 * Test method
	 */
	@Test
	public void test()
	{
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		
		try
		{
			DAO.deleteUser((DAO.findUser("", "")).getId());
		} catch (ToDoListDaoException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
