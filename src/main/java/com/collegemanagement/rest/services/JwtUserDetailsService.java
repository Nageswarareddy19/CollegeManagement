package com.collegemanagement.rest.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.collegemanagement.rest.dao.UserDao;
import com.collegemanagement.rest.entity.UserEntity;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao dao;
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	public String saveUser(UserEntity entity) {

		entity.setPassword(pwdEncoder.encode(entity.getPassword()));

		return dao.saveUser(entity);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity user = dao.findByUserName(username);

		if (user != null) {
			return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

	}

}
