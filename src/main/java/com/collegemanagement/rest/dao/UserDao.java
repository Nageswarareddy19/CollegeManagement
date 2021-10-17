package com.collegemanagement.rest.dao;

import com.collegemanagement.rest.entity.UserEntity;

public interface UserDao {

	
	public String saveUser(UserEntity entity);
	public  UserEntity findByUserName(String name);
	
}
