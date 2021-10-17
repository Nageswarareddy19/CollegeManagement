package com.collegemanagement.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collegemanagement.rest.entity.UserEntity;
import com.collegemanagement.rest.securityconfig.JwtRequest;
import com.collegemanagement.rest.securityconfig.JwtResponse;
import com.collegemanagement.rest.securityconfig.JwtUtil;
import com.collegemanagement.rest.services.JwtUserDetailsService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUserDetailsService service;
	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<String> registerUser(@RequestBody UserEntity entity) {

		String result = service.saveUser(entity);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@PostMapping(value = "/login")
	public ResponseEntity<JwtResponse> loginUser(@RequestBody JwtRequest jwtRequest) {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		String token = jwtUtil.generateToken(jwtRequest.getUsername());
		return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);

	}
}
