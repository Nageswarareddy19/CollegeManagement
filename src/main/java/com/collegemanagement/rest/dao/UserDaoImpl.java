package com.collegemanagement.rest.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.collegemanagement.rest.entity.UserEntity;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory factory;

	private Session getSession() {
		Session session = null;
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException ex) {
			session = factory.openSession();
		}
		return session;
	}

	@Override
	public String saveUser(UserEntity entity) {

		getSession().save(entity);

		return "User Details saved successfully";
	}

	@Override
	@SuppressWarnings({ "unchecked", "deprecation" })
	public UserEntity findByUserName(String name) {

		Query query = getSession().createQuery("from UserEntity where username=:username");
		query.setParameter("username", name);

		List<UserEntity> list = query.list();
		return list.get(0);
	}

}
