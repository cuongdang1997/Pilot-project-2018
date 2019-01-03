package com.magrabbit.pilot.dao.base;

import java.io.Serializable;

/**
 * Interface CrudDao 
 * @author cuong
 *
 * @param <E>
 * @param <K>
 */

public interface CrudDao <E, K extends Serializable> {

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
}
