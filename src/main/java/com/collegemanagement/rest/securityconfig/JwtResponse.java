package com.collegemanagement.rest.securityconfig;

import java.io.Serializable;

public class JwtResponse implements Serializable {



	private static final long serialVersionUID = 4082055285064020987L;
	private final String jwtToken;

	public JwtResponse(String jwttoken) {
		this.jwtToken = jwttoken;
	}

	public String getToken() {
		return this.jwtToken;
	}

}
