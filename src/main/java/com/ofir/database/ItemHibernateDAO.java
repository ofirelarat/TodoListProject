package com.ofir.database;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ofir.database.interfaces.Creatable;
import com.ofir.database.interfaces.Deletable;
import com.ofir.database.interfaces.Readable;
import com.ofir.database.interfaces.Updatable;
import com.ofir.exception.ToDoListDaoException;
import com.ofir.model.Item;
import com.ofir.model.User;

public class ItemHibernateDAO implements Creatable<Item>, Readable<Item>, Updatable<Item>, Deletable<Item> {

	private HibernateDAO<Item> DAO;
	
	public ItemHibernateDAO() {
		DAO = new HibernateDAO<>(Item.class);
	}
	
	@Override
	public void delete(Item item) throws ToDoListDaoException {
		DAO.delete(item);
	}

	@Override
	public void update(Item item) throws ToDoListDaoException {
		DAO.update(item);
	}

	@Override
	public Optional<Item> read(Object id) throws ToDoListDaoException {
		return DAO.read(id);
	}

	@Override
	public List<Item> readAll() {
		return DAO.readAll();
	}

	@Override
	public void create(Item item) throws ToDoListDaoException {
		DAO.create(item);
	}

	public List<Item> getUserItems(User user){
		List<Item> itemsList = Collections.emptyList();
		Session session = HibernateConnect.getSesstion().openSession();

		String hql = String.format("FROM Item I WHERE I.userEmail=:userEmail");
		Query<Item> query = session.createQuery(hql);
		query.setParameter("userEmail", user.getEmail());
		itemsList =  query.getResultList();
		
		session.close();
		
		return itemsList;
	}
}
