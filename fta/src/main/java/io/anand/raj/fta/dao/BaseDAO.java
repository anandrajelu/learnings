package io.anand.raj.fta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

/**
 * @author aelumalai
 *
 */

@Transactional
@Repository
public class BaseDAO<T> {
	
	@PersistenceContext
	EntityManager em;
	
	Class<T> entity;
	
	public boolean save(T t) {
		em.persist(t);
		return true;
	}
	
	public T findById(Long id) {
		return em.find(entity, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return em.createQuery(" from "+entity.getName()).getResultList();
	}
	
	public T update(T t) {
		return em.merge(t);
	}
	
	public void setEntity(Class<T> entity) {
		this.entity = entity;
	}
}
