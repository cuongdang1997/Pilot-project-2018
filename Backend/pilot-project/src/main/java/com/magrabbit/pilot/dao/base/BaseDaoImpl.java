package com.magrabbit.pilot.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
@SuppressWarnings("unchecked")
@Transactional
public class BaseDaoImpl<E, K extends Serializable> implements BaseDao<E, K> {

	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Class<? extends E> daoType;
	
	public BaseDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		daoType = (Class<? extends E>) pt.getActualTypeArguments()[0];
	}
	
	/**
	 * Get Session
	 * 
	 * @return session
	 */
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.setFlushMode(FlushMode.MANUAL);
		return currentSession;
	}
	
	/**
	 * Flush Data When Update, Insert, Delete
	 */
	private void flush() {
		getSession().flush();
		getSession().clear();
	}
	
	/**
	 * Insert Entity
	 * 
	 * @param entity
	 */
	@Override
	public K save(E entity) {
		K key = (K) getSession().save(entity);
		flush();
		return key;
	}

	/**
	 * Update Entity
	 * 
	 * @param entity
	 */
	@Override
	public void update(E entity) {
		getSession().update(entity);
		flush();
	}

	/**
	 * Save or Update
	 * 
	 * @param entity
	 */
	@Override
	public void saveOrUpdate(E entity) {
		getSession().saveOrUpdate(entity);
		flush();
	}

	/**
	 * Delete Entity
	 * 
	 * @param entity
	 */
	@Override
	public void delete(E entity) {
		getSession().delete(entity);
		flush();
	}

	/**
	 * Delete Entity with key
	 * 
	 * @param key
	 */
	@Override
	public void delete(K key) {
		E entity = find(key);
		delete(entity);
	}

	/**
	 * 
	 * @param criteria
	 * @return Object
	 * @throws Exception
	 */
	@Override
	public Object uniqueResult(Criteria criteria) {
		checkIfCriteriaNull(criteria);
		return criteria.uniqueResult();
	}

	/**
	 * Count All Record
	 * 
	 * @return Record Number
	 */
	@Override
	public long countAll() {
		return (Long) getSession().createCriteria(daoType).setProjection(Projections.rowCount()).uniqueResult();
	}
	
	/**
	 * count criteria
	 */
	@Override
	public long countCriteria(Criteria criteria) {
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	public long countQuery(String sqlQuery) {
		Query query = getSession().createSQLQuery(sqlQuery);
		BigInteger bigValue = (BigInteger) query.uniqueResult();
		return (Long) bigValue.longValue();
	}
	
	@Override
	public E find(K key) {
		return (E) getSession().get(daoType, key);
	}

	public List<E> search(Criteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get Record in the Table
	 * 
	 * @return List<E>
	 */
	@Override
	public List<E> getAll() {
		return getSession().createCriteria(daoType).list();
	}
	
	/**
	 * Check Criteria Null
	 * 
	 * @param query
	 */
	private void checkIfCriteriaNull(Criteria criteria) {
		if (criteria == null)
			throw new NullPointerException("Criteria not set.");
	}

	/**
	 * find item from start with maxResult rows
	 */
	@Override
	public List<E> findAll(int start, int maxResult) {
		return getSession().createCriteria(daoType).setFirstResult(start).setMaxResults(maxResult).list();
	}

	@Override
	public long countCriteria(Map<String, Object> mapCriteria) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
