package com.cex.application.repository.common;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public abstract class EntityDao<T>
{
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	private final Class<T> classe = getGenericClass();
	private final String GET = "get";
	
	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	private Class<T> getGenericClass() {
		Class<?> classe = null;
		try {
	        String className = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
	        classe = Class.forName(className);
	    } catch (Exception e) {
	        log.error("Classe non parametrizzata con i generics");
	        log.error(e.getMessage());
	    }
	    return (Class<T>)classe;
	}
	
	protected T get(Serializable id) 
	{
		T entity = null;
		try {
			entity = em.getReference(classe, id);
		} catch (HibernateException | IllegalArgumentException e) {
			log.error(e.getMessage());
		}
		return entity;
	}
	
	protected T insert(T entity) 
	{
		try {
			em.persist(entity);
			em.flush();
			em.clear();
		} catch (HibernateException e) {
			entity = null;
			log.error(e.getMessage());
		}
		return entity;
	}
	
	protected T update(T entity) 
	{
		try {
			em.merge(entity);
			em.flush();
			em.clear();
		} catch (HibernateException e) {
			entity = null;
			log.error(e.getMessage());
		}
		return entity;
	}
	
	protected T delete(Serializable id) 
	{
		T entity = em.getReference(classe, id);
		try {
			em.remove(entity);
			em.flush();
			em.clear();
		} catch (HibernateException | IllegalArgumentException e) {
			log.error(e.getMessage());
		}
		return entity;
	}

	protected List<T> findByCriteria(T entity)
	{
		List<T> lista = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<T> cr = cb.createQuery(classe);
			Root<T> root = cr.from(classe);
			cr.select(root);
			
			ArrayList<Predicate> filtro = new ArrayList<Predicate>();
			Method[] metodi = classe.getDeclaredMethods();
			for(Method metodo: metodi) {
				if(metodo.getName().startsWith(GET)) {
					Object expression = metodo.invoke(entity);
					if(expression!=null) {
						String propertyName = getProperyNameFromMethod(metodo.getName());
						filtro.add(cb.equal(root.get(propertyName), expression));
					}
				}
			}
//			cr.where(filtro.toArray(new Predicate[0]));
			cr.where(filtro.toArray(Predicate[]::new));
			
			TypedQuery<T> query = em.createQuery(cr);
			lista = query.getResultList();
			
		} catch (HibernateException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
			log.error(e.getMessage());
		}
		
		return lista;
	}
	
	protected List<T> findByProperty(String propertyName, Object value)
	{
		List<T> lista = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<T> cr = cb.createQuery(classe);
			Root<T> root = cr.from(classe);
			cr.select(root);
			
			ArrayList<Predicate> filtro = new ArrayList<Predicate>();
			if(value!=null) 
			{
				filtro.add(cb.equal(root.get(propertyName), value));
			}
//			cr.where(filtro.toArray(new Predicate[0]));
			cr.where(filtro.toArray(Predicate[]::new));
			
			TypedQuery<T> query = em.createQuery(cr);
			lista = query.getResultList();
			
		} catch (HibernateException | IllegalArgumentException | SecurityException e) {
			log.error(e.getMessage());
		}
		
		return lista;
	}
	
	private String getProperyNameFromMethod(String methodName) {
		String propertyName = methodName.substring(3);
		propertyName = propertyName.substring(0,1).toLowerCase() + propertyName.substring(1);
		return propertyName;
	}
	
	protected static <T> Class<?> getClasse(T entity){
		return entity.getClass();
	}
	
}
