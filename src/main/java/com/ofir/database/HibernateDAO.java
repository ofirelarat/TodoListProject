package com.ofir.database;

import com.ofir.database.interfaces.Creatable;
import com.ofir.database.interfaces.Deletable;
import com.ofir.database.interfaces.Readable;
import com.ofir.database.interfaces.Updatable;
 
public class HibernateDAO <T> implements Creatable<T>,Readable<T>,Updatable<T>, Deletable<T>{

	@Override
	public void create(T item) {
		// TODO Auto-generated method stub
		
	}
}
