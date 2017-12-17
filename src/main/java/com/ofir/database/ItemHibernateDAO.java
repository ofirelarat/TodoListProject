package com.ofir.database;

import java.util.List;
import java.util.Optional;

import com.ofir.database.interfaces.Creatable;
import com.ofir.database.interfaces.Deletable;
import com.ofir.database.interfaces.Readable;
import com.ofir.database.interfaces.Updatable;
import com.ofir.exception.ToDoListDaoException;
import com.ofir.model.Item;

public class ItemHibernateDAO implements Creatable<Item>, Readable<Item>, Updatable<Item>, Deletable<Item> {

	private HibernateDAO<Item> DAO;
	
	public ItemHibernateDAO() {
		DAO = new HibernateDAO<>(Item.class);
	}
	
	@Override
	public void delete(Item item) throws ToDoListDaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Item item) throws ToDoListDaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Item> read(Object id) throws ToDoListDaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Item item) throws ToDoListDaoException {
		// TODO Auto-generated method stub
		
	}

}
