package io.anand.raj.springh2db.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository
public abstract class BaseDAO<T, ID extends Serializable> {
	
	@PersistenceContext
	protected EntityManager em;
	
	private Class<T> entityClass;
	
	public BaseDAO() {
		entityClass = (Class<T>)((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void persist(T t) {
		em.persist(t);
	}
	
	public T update(T t) {
		return em.merge(t);
	}
	
	public void delete(T t) {
		em.remove(t);
	}
	
	public List<T> findAll() {
		return em.createQuery(" from " + entityClass.getName()).getResultList();
	}
	
	public T find(ID id) {
		return em.find(entityClass, id);
	}
	
	public void deleteById(ID id) {
		T entity = find(id);
		em.remove(entity);
	}
}
