package com.ofir.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnect {
	private SessionFactory factory;
	private static Object SYNC_FLAG = new Object();
	private static HibernateConnect instance = null;
	
	private HibernateConnect(){
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
	}
	
	public static SessionFactory getSesstion(){
		if(instance == null){
			synchronized (SYNC_FLAG) {
				if(instance == null){
					instance = new HibernateConnect();
				}
			}
		}
		
		return instance.factory;
	}
}
