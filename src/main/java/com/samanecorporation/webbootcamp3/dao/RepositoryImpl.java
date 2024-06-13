package com.samanecorporation.webbootcamp3.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.samanecorporation.webbootcamp3.config.HibernateUtil;

public class RepositoryImpl<T> implements Repository<T> {
	
	private Session session = HibernateUtil.getSessionFactory().openSession();
	
	@Override
	@Transactional
	public boolean add(T t) {
		try {
			session.save(t);
			return true;
		} catch (Exception e2) {
			session.flush();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean delete(long id,T t) {
				
		try {
			session.delete(session.get(t.getClass(), id));
			return true;
		} catch (Exception e2) {
			session.flush();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(T t) {
		try {
			session.merge(t);
			return true;
		} catch (Exception e2) {
			session.flush();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(T t) {
		
       CriteriaBuilder cb = session.getCriteriaBuilder();
      
       CriteriaQuery<T> cq = (CriteriaQuery<T>) cb.createQuery(t.getClass());
       Root<T> root = (Root<T>) cq.from(t.getClass());
       							cq.select(root);
              
       return session.createQuery(cq).getResultList();
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get (long id,T t) {
		
        return (T) session.get(t.getClass(), id);
	}

	public Session getSession() {
		return session;
	}

}