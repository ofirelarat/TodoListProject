package com.ofir.database.interfaces;

public interface Readable <T>{
	public T read(Object id);
	public List<T> readAll();
}
