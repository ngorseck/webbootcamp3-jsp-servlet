package com.samanecorporation.webbootcamp3.dao;

import java.util.List;

public interface Repository <T> {
	public boolean add(T t);
	public boolean delete(long id,T t);
	public boolean update(T t);
	public List<T> list(T t);
	public T get(long id,T t);
}
