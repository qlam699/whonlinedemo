package com.donglam.webhoconline.service;

import java.util.List;

public interface GenericService<E, K> {

	public void add(E entity);

	public void update(E entity);

	public void delete(E entity);

	public void saveOrUpdate(E entity);

	public E get(K key);

	public List<E> getList();
}
