package com.ofir.database.interfaces;

import com.ofir.exception.ToDoListDaoException;

public interface Deletable <T> {
	public void delete(T item) throws ToDoListDaoException;
}
