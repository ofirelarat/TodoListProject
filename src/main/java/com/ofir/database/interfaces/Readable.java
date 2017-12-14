package com.ofir.database.interfaces;

import java.util.List;

public interface Readable <T>{
	public T read(Object id);
	public List<T> readAll();
}
