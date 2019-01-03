package com.magrabbit.pilot.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;

public interface BaseService<E, K extends Serializable> {

	/**
	 * Get unique result
	 * 
	 * @param criteria
	 * @return Object
	 * @throws Exception
	 */
	Object uniqueResult(Criteria criteria) throws Exception;

	/**
	 * Count All Record
	 * 
	 * @return Record Number
	 */
	long countAll();

	/**
	 * Count All record with Map Criteria
	 * 
	 * @param mapCriteria
	 * @return Record Number
	 */
	long countCriteria(Map<String, Object> mapCriteria);

	/**
	 * Find Object with Key
	 * 
	 * @param key
	 * @return Object
	 */
	E find(K key);

	/**
	 * Search List with criteria
	 * 
	 * @param criteria
	 * @return List<E>
	 */
	List<E> search(Criteria criteria);

	/**
	 * Get Record in the Table
	 * 
	 * @return List<E>
	 */
	List<E> getAll();

	/**
	 * Insert Entity
	 * 
	 * @param entity
	 */
	K save(E entity);

	/**
	 * Update Entity
	 * 
	 * @param entity
	 */
	void update(E entity);

	/**
	 * Save or Update
	 * 
	 * @param entity
	 */
	void saveOrUpdate(E entity);

	/**
	 * Delete Entity
	 * 
	 * @param entity
	 */
	void delete(E entity);

	/**
	 * Delete Entity with key
	 * 
	 * @param key
	 */
	void delete(K key);
	
	/**
	 * find all from start with maxResult rows
	 * @param start
	 * @param maxResult
	 * @return
	 */
	public List<E> findAll(int start, int maxResult);
	
}
