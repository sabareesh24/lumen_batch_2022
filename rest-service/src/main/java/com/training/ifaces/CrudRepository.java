package com.training.ifaces;

import com.training.exceptions.ElementNotFoundException;
import com.training.model.Student;
import java.util.*;
public interface CrudRepository<T> {

	public boolean save(T obj);
	public Collection<T> findAll();
	public boolean delete(T obj) throws ElementNotFoundException;
	public boolean deleteById( int key) throws ElementNotFoundException;
	public T findById(int key) throws ElementNotFoundException;
	public int update(T obj);
}
