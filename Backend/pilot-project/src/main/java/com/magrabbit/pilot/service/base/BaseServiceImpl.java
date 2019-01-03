package com.magrabbit.pilot.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magrabbit.pilot.dao.base.BaseDao;

/**
 * Base Service Implementation
 * 
 * @author ThienMai
 * @since 17-10-2016
 */
@Service
@Transactional
public abstract class BaseServiceImpl<E, K extends Serializable> implements BaseService<E, K> {

	protected final Logger LOGGER = Logger.getLogger(getClass());

	private BaseDao<E, K> baseDao;
	public BaseServiceImpl(BaseDao<E, K> baseDao) {
		this.baseDao = baseDao;
	}

	public BaseServiceImpl() {
		
	}

	/**
	 * 
	 * @param criteria
	 * @return Object
	 * @throws Exception
	 */
	public Object uniqueResult(Criteria criteria) throws Exception {
		return baseDao.uniqueResult(criteria);
	}

	/**
	 * Count All Record
	 * 
	 * @return Record Number
	 */
	public long countAll() {
		return baseDao.countAll();
	}

	/**
	 * Count All record with Map Criteria
	 * 
	 * @param mapCriteria
	 * @return Record Number
	 */
	public long countCriteria(Map<String, Object> mapCriteria) {
		return baseDao.countCriteria(mapCriteria);
	}

	/**
	 * Find Object with Key
	 * 
	 * @param key
	 * @return Object
	 */
	public E find(K key) {
		return baseDao.find(key);
	}


	/**
	 * Search List with criteria
	 * 
	 * @param criteria
	 * @return List<E>
	 */
	public List<E> search(Criteria criteria) {
		return baseDao.search(criteria);
	}

	/**
	 * Get Record in the Table
	 * 
	 * @return List<E>
	 */
	public List<E> getAll() {
		return baseDao.getAll();
	}

	/**
	 * Insert Entity
	 * 
	 * @param entity
	 */
	public K save(E entity) {
		return baseDao.save(entity);
	}

	/**
	 * Update Entity
	 * 
	 * @param entity
	 */
	public void update(E entity) {
		baseDao.update(entity);
	}

	/**
	 * Save or Update
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(E entity) {
		baseDao.saveOrUpdate(entity);
	}

	/**
	 * Delete Entity
	 * 
	 * @param entity
	 */
	public void delete(E entity) {
		baseDao.delete(entity);
	}

	/**
	 * Delete Entity with key
	 * 
	 * @param key
	 */
	public void delete(K key) {
		baseDao.delete(key);
	}
	
	/**
	 * find all from start with maxResult rows
	 * @param start
	 * @param maxResult
	 * @return
	 */
	public List<E> findAll(int start, int maxResult){
		return baseDao.findAll(start, maxResult);
	}
}