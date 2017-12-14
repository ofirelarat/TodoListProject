package com.ofir.database.interfaces;

import com.ofir.exception.ToDoListDaoException;

public interface Creatable <T> {
	public void create(T item) throws ToDoListDaoException;
}
