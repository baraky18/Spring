package com.security.SpringSecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import com.security.SpringSecurity.model.MyUserDetails;

@Configuration
public class BeanConfiguration {

	@Bean
	public UserDetails getUserDeails(){
		return new MyUserDetails();
	}
}
