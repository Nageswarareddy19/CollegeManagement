package com.collegemanagement.rest.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.collegemanagement.rest.services.JwtUserDetailsService;

@Configuration
@EnableWebSecurity	
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	public WebSecurityConfig() {
		
		System.out.println("WebSecurityConfig.WebSecurityConfig()");
	}

	@Autowired
	private InvalidUserAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private JwtUserDetailsService jwtUserDetailService;

	@Autowired
	private SecurityFilter securityFilter;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(jwtUserDetailService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/user/save", "user/login").permitAll()
		.anyRequest().authenticated()
		.and()
				
		.exceptionHandling().
		authenticationEntryPoint(authenticationEntryPoint)
		.and()
		
		.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore( securityFilter,UsernamePasswordAuthenticationFilter.class);
				
		

	}
}		