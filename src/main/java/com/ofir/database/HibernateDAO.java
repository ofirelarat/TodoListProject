package com.ofir.database;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ofir.database.interfaces.Creatable;
import com.ofir.database.interfaces.Deletable;
import com.ofir.database.interfaces.Readable;
import com.ofir.database.interfaces.Updatable;
import com.ofir.exception.ToDoListDaoException;
 
public class HibernateDAO <T> implements Creatable<T>,Readable<T>,Updatable<T>, Deletable<T> {

	private Class objectClass;
	
	public HibernateDAO(Class obClass) {
		this.objectClass = obClass;
	}
	
	@Override
	public void create(T item) throws ToDoListDaoException {
		Session session = HibernateConnect.getSesstion().openSession();
		try{
			session.beginTransaction();
			session.save(item);
			session.getTransaction().commit();
		}
		catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new ToDoListDaoException(e.getMessage(), e);
		}
		finally {
			session.close();
		}
	}

	@Override
	public void delete(T item) throws ToDoListDaoException {
		Session session = HibernateConnect.getSesstion().openSession();
		try{
			session.beginTransaction();
			session.delete(item);
			session.getTransaction().commit();
		}
		catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new ToDoListDaoException(e.getMessage(), e);
		}
		finally {
			session.close();
		}
	}

	@Override
	public void update(T item) throws ToDoListDaoException {
		Session session = HibernateConnect.getSesstion().openSession();
		try{
			session.beginTransaction();
			session.update(item);
			session.getTransaction().commit();
		}
		catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new ToDoListDaoException(e.getMessage(), e);
		}
		finally {
			session.close();
		}
	}

	@Override
	public Optional<T> read(Object id) throws ToDoListDaoException {
		Session session = HibernateConnect.getSesstion().openSession();
		Optional<T> optionalObject = Optional.empty();
		try{
			session.beginTransaction();
			if(id.getClass().getName().equals("Integer")){
				T object = (T) session.get(this.objectClass, (int)id);
				if(object != null){
					optionalObject = Optional.of(object);
				}
			}
			session.getTransaction().commit();
		}
		catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new ToDoListDaoException(e.getMessage(), e);
		}
		finally {
			session.close();
		}
		
		return optionalObject;
	}

	@Override
	public List<T> readAll() {
		Session session = HibernateConnect.getSesstion().openSession();
		List<T> objectList = Collections.emptyList();
		session.beginTransaction();
		objectList = session.createCriteria(objectClass).list();
		session.close();
		
		return objectList;
	}
}
