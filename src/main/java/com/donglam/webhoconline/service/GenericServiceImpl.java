package com.donglam.webhoconline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.donglam.webhoconline.dao.GenericDao;

import java.util.List;

@Service
public abstract class GenericServiceImpl<E, K> implements GenericService<E, K> {
	@Autowired
	private GenericDao<E, K> genericDao;

	@Autowired
	public GenericServiceImpl(GenericDao<E, K> genericDao) {
		this.genericDao = genericDao;
	}

	public GenericServiceImpl() {
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrUpdate(E entity) {
		genericDao.saveOrUpdate(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<E> getList() {
		return genericDao.getList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public E get(K id) {
		return genericDao.get(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(E entity) {
		genericDao.add(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(E entity) {
		genericDao.update(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(E entity) {
		genericDao.delete(entity);
	}
}
