package com.collegemanagement.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collegemanagement.rest.dao.DepartmentDao;
import com.collegemanagement.rest.entity.DepartmentEntity;


@Service
public class DepartmentService {

	@Autowired
	private DepartmentDao dao;

	public String saveDeptDetails(DepartmentEntity entity) {

		return dao.addDepartment(entity);

	}

}
