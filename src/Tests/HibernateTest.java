package Tests;

import org.junit.Test;

import Exceptions.ToDoListDaoException;
import Model.*;
import Model.Item.Status;

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
		User user1 = new User ("san@g.com", "San", "Zafrir" , "1234");
		User user2 = new User("ofir@ofir", "Ofir", "Elarat" , "ofir111");
		Item item1 = new Item("something to do 1", "bla bla bla", Status.ready, user1.getEmail());
		Item item2 = new Item("something to do 2", "bla bla bla bla bla", Status.ready, user1.getEmail());
		
		IToDoListDAO DAO = HibernateToDoListDAO.getInstance();

		try{
			DAO.addUser(user1);
			DAO.addUser(user2);
			DAO.addItem(item1);
			DAO.addItem(item2);
			
			user1 = DAO.findUser("san@g.com", "1234");
			System.out.println(user1.toString());
			user1.setFirstName("Chezna");
			System.out.println(user1.toString());
			DAO.editUser(user1);
			
			Item[] items = DAO.getItemsOfUser(user1);
			for (Item item : items)
			{
				System.out.println(item.toString());
				item.setStatus(Status.inProgress);
				DAO.editItem(item);
				System.out.println(item.toString());
			}
			
		}
		catch (ToDoListDaoException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
