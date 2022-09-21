package com.example.ifaces;

import java.util.*;

import com.example.exceptions.ElementNotFoundException;
public interface CrudRepository<T> {

	public boolean save(T obj);
	public Collection<T> findAll();
	public boolean delete(T obj) throws ElementNotFoundException;
	public boolean deleteById( int key) throws ElementNotFoundException;
	public T findById(int key) throws ElementNotFoundException;
	public Collection<T> findByName(String name) throws ElementNotFoundException;
	public int update(T obj);
}
