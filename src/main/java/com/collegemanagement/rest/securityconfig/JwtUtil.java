package com.collegemanagement.rest.securityconfig;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	
	public JwtUtil() {
		System.out.println("JwtUtil.JwtUtil()");
	}
	

	@Value("${jwt.secret}")
	private String secret;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	// Generate Token
	private String generateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuer("College")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	// Read Cliam
	private Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// Read Exp Date
	public Date getExpDate(String token) {
		return getClaims(token).getExpiration();
	}

	// Read username
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	// Generate Token with Empty Claims
	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		return generateToken(claims, username);
	}

	//Check current date exp date
	
	private boolean isTokenExpired(String token) {
		final Date expiration = getExpDate(token);
		return expiration.before(new Date());
	}

	//validate token user name and request user also expDate
	public boolean validateToken(String token, String  userName) {
		System.out.println("validateToken() method is called");
		String usernameInToken = getUsername(token);
		return (usernameInToken.equals(userName) && !isTokenExpired(token));
	}
	
	
}
	