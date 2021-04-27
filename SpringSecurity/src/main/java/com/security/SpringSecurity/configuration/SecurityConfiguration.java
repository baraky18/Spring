package com.security.SpringSecurity.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.SpringSecurity.filter.JwtRequestFilter;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	/*
	 * this is the DB that's connected to the app. it could be an embedded DB (like H2)
	 * or an Oracle DB
	 */
	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{

		/*
		 * this is how we do a LDAP authentication
		 */
		auth.ldapAuthentication()
		.userDnPatterns("uid={0},ou=people")
		.groupSearchBase("ou=groups")
		.contextSource()
		.url("ldap://localhost:8389/dc=springframework,dc=org")
		.and()
		.passwordCompare()
		.passwordEncoder(new LdapShaPasswordEncoder())
		.passwordAttribute("userPassword");

		/*
		 * this is how we do a JPA authentication
		 */
		auth.userDetailsService(userDetailsService);

		/*
		 * this is how we do JDBC authentication with an external DB (like Oracle).
		 * we need also to configure the DB connection details in the properties file
		 */
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select username, password, enabled from users where username = ?")
		.authoritiesByUsernameQuery("select username, authority from authorities where username = ?");

		/*
		 * this is how we do JDBC authentication with an internal DB (like H2) that's using the default schema.
		 */
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.withDefaultSchema()
		.withUser(
				User.withUsername("user")
				.password("pass")
				.roles("USER")
				)
		.withUser(
				User.withUsername("admin")
				.password("pass")
				.roles("ADMIN")
				);

		/*
		 * In-memory authentication is the simplest form, and requires that the credentials for all users be specified 
		 * in the code itself. It doesn't require any data configuration. This is impractical in all but the simplest cases
		 */
		auth.inMemoryAuthentication()
		.withUser("someUser")
		.password("somePassword")
		.roles("USER")
		.and()
		.withUser("anotherUser")
		.password("anotherPassword")
		.roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * ----- Related to all authentication (except JWT) -----
		 * since antMatchers method can accept wild card String like "/**", it's important to put it in the end of the expression,
		 * because if not, the other authorizations will not get caught
		 */
		http.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user").hasAnyRole("USER", "ADMIN")
		.antMatchers("/").permitAll()
		.and()
		.formLogin();
		
		/*
		 * ----- Related to JWT authentication -----
		 * since Spring security is authenticating every end point, it's not necessary to do that when using URI "/authenticate", 
		 * since this end point already authenticate using jwt. so we need to tell Spring security to NOT authenticate this end point. 
		 * we do that by adding the following code
		 */
		http.csrf().disable()
			.authorizeRequests().antMatchers("/authentivate").permitAll()
			.anyRequest().authenticated()
			.and()
			/*
			 * since the app is using jwt, no sessions managing is required, since we are sending jwt with every request.
			 * that means that the session should be stateless
			 */
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		/*
		 * we need to tell Spring where to push the jwt filter that we created
		 */
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
}
