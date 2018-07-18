package com.donglam.webhoconline.dao;

import java.util.List;

public interface GenericDao<E, K> {
	public void add(E entity);

	public void update(E entity);

	public void delete(E entity);

	public void saveOrUpdate(E entity);

	public E get(K key);

	public List<E> getList();
}
