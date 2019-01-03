package com.magrabbit.pilot.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;

public interface BaseDao<E, K extends Serializable> extends CrudDao<E, K> {

	/**
	 * Get Session
	 * 
	 * @return session
	 */
	Session getSession();

	/**
	 * Search with unique result
	 * 
	 * @param criteria
	 * @return Object
	 * @throws Exception
	 */
	Object uniqueResult(Criteria criteria) throws Exception;

	/**
	 * Count All Record
	 * @return number of record
	 */
	long countAll();

	/**
	 * Count All record with Map Criteria
	 * 
	 * @param mapCriteria
	 * @return number of record
	 */
	long countCriteria(Map<String, Object> mapCriteria);
	
	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public long countCriteria(Criteria criteria);

	/**
	 * Count All record with query
	 * 
	 * @param query
	 * @return number of record
	 */
	long countQuery(String query);

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
	
	List<E> findAll(int start, int maxResults);
	
}
