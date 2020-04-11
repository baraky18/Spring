package com;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//this file can replace the DispatcherServlet configuration in web.xml
public class MyWebInit extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{MyConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

}
