package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//this file can replace newServlet-servlet.xml
@Configuration
@ComponentScan({"com"})
public class MyConfiguration {
	
	@Bean
	public InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/");//sets the location of the jsp files
		vr.setSuffix(".jsp");//sets the suffix of the jsp files in case we want to change it in the future, we only need to update that here
		return vr;
	}
	
}
