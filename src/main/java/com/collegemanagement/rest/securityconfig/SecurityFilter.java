package com.collegemanagement.rest.securityconfig;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.collegemanagement.rest.services.JwtUserDetailsService;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	public SecurityFilter() {
		System.out.println("SecurityFilter.SecurityFilter()");
	}
	

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = request.getHeader("Authorization");

		System.out.println("token is::" + token);

		if (token != null) {

			String userName = jwtUtil.getUsername(token);

			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails user = jwtUserDetailsService.loadUserByUsername(userName);

				boolean isValid = jwtUtil.validateToken(token, user.getUsername());

				if (isValid) {

					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, user.getPassword(),
							user.getAuthorities());

					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(authToken);

				}
			}
		}

		filterChain.doFilter(request, response);
	}

}
