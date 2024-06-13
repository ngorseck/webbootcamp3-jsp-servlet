package com.samanecorporation.webbootcamp3.dao;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samanecorporation.webbootcamp3.config.HibernateUtil;
import com.samanecorporation.webbootcamp3.entities.UserAccountEntity;

public class UserAccountDao extends RepositoryImpl<UserAccountEntity> implements IUserAccountDao {
	private Logger logger = LoggerFactory.getLogger(UserAccountDao.class);
	
	// API critria
	@Override
	public Optional<UserAccountEntity> login (String email, String pwd) {
		logger.info("Email doa : {}", email);
		CriteriaBuilder cb = this.getSession().getCriteriaBuilder();
		CriteriaQuery<UserAccountEntity> cr = cb.createQuery(UserAccountEntity.class);
		Root<UserAccountEntity> user = cr.from(UserAccountEntity.class);
		//Predicate pour la clause where
		Predicate predicateEmail = cb.equal(user.get("email"), email);
		Predicate predicatePwd = cb.equal(user.get("password"), pwd);
		Predicate finalPredicate = cb.and(predicateEmail, predicatePwd);
		
		cr.select(user);
		cr.where(finalPredicate);
		
		return Optional.ofNullable(this.getSession().createQuery(cr).getSingleResult());
	}
	
}
