package com.ofir.database.interfaces;

import java.util.List;
import java.util.Optional;

import com.ofir.exception.ToDoListDaoException;

public interface Readable <T>{
	public Optional<T> read(Object id) throws ToDoListDaoException;
	public List<T> readAll();
}
