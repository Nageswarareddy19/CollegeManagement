package com.collegemanagement.rest.securityconfig;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringWebInitializer  extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	public SpringWebInitializer() {
		System.out.println("SpringWebInitializer.SpringWebInitializer()");
	}
	

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {WebSecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return  new String[] {"/"};
	}

}
