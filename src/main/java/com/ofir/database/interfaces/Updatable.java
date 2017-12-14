package com.ofir.database.interfaces;

import com.ofir.exception.ToDoListDaoException;

public interface Updatable <T>{
	public void update(T item) throws ToDoListDaoException;
}
