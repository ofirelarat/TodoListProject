package com.ofir.database;

import java.util.List;
import java.util.Optional;

import com.ofir.database.interfaces.Creatable;
import com.ofir.database.interfaces.Deletable;
import com.ofir.database.interfaces.Readable;
import com.ofir.database.interfaces.Updatable;
import com.ofir.exception.ToDoListDaoException;
import com.ofir.model.User;

public class UserHibernateDAO implements Creatable<User>, Readable<User>, Updatable<User>, Deletable<User> {
	private HibernateDAO<User> DAO;
	
	public UserHibernateDAO(){
		DAO = new HibernateDAO<>(User.class);
	}

	@Override
	public void delete(User item) throws ToDoListDaoException {
		DAO.delete(item);
	}

	@Override
	public void update(User item) throws ToDoListDaoException {
		DAO.update(item);
	}

	@Override
	public Optional<User> read(Object id) throws ToDoListDaoException {
		return DAO.read(id);
	}

	@Override
	public List<User> readAll() {
		return DAO.readAll();
	}

	@Override
	public void create(User item) throws ToDoListDaoException {
		DAO.create(item);
	}

}
