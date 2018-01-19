package com.ofir.database;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
		if(!isExist(item.getEmail())){
			DAO.create(item);
		}
		else{
			throw new RuntimeException("User Already exist");
		}
	}
	
	public Optional<User> loginUser(String email, String password) throws ToDoListDaoException{
		Session session = HibernateConnect.getSesstion().openSession();
		Optional<User> optionalUser = Optional.empty();
		try{
			String hql = String.format("FROM User U WHERE U.email=:email and U.password=:password");
			Query<User> query = session.createQuery(hql);
			query.setParameter("email", email);
			query.setParameter("password", password);
			optionalUser = query.uniqueResultOptional();
		}
		catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new ToDoListDaoException("Error. can not find user - " + e.getMessage(), e);
		}
		finally{
			session.close();
		}
			
		return optionalUser;
	}

	private boolean isExist(String email){
		Session session = HibernateConnect.getSesstion().openSession();
		String hql = String.format("FROM User U WHERE U.email=:email");
		Query query = session.createQuery(hql);
		query.setParameter("email", email);	
		boolean isExist = query.uniqueResultOptional().isPresent();
		session.close();
		
		return isExist;
	}
}
