package com.collegemanagement.rest.dao;

import java.util.List;

import com.collegemanagement.rest.entity.DepartmentEntity;

public interface DepartmentDao {

	public String addDepartment(DepartmentEntity entity);
	public List<DepartmentEntity> listOfDeptByCollegeName(String collegeName);

}
