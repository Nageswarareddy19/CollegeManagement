package com.collegemanagement.rest.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.collegemanagement.rest.entity.CollegeEntity;
import com.collegemanagement.rest.entity.DepartmentEntity;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

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
	public String addDepartment(DepartmentEntity entity) {

		Serializable save = getSession().save(entity);
		System.out.println(save.toString());
		return "Department Details added successfully";
	}

	/*
	 * 
	 * private Integer deptId; private String departmentName; private String
	 * hodName;
	 * 
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentEntity> listOfDeptByCollegeName(String collegeName) {


		Query query = getSession().createSQLQuery("select collegeId from college where name="+"'"+collegeName+"'");
		
		 Integer collegeId = (Integer)query.uniqueResult();
		Query q = getSession().createSQLQuery("select * from  department where college_id="+collegeId);		
		return (List<DepartmentEntity>)q.list();

		

		
	}

}
