package com.lucianaugusto.mypetclinic.services;

import java.util.Set;

public interface CrudService<T, ID> {
//	Interface that mimics the Spring Data Repositories and is inherited by all Data Services in the application

	Set<T> findAll();
	
	T findById(ID id);
	
	T save(T object);
	
	void delete(T object);
	
	void deleteById(ID id);
}
