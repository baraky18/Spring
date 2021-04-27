package com.security.SpringSecurity.filter;


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
import org.springframework.web.filter.OncePerRequestFilter;

import com.security.SpringSecurity.service.MyUserDetailsService;
import com.security.SpringSecurity.util.JwtUtil;

/*
 * the way to intercept each request that's called from user to check if it contains a jwt is to create a filter
 */
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, FilterChain paramFilterChain)
					throws ServletException, IOException {
		final String authorizationHeader = paramHttpServletRequest.getHeader("Authorization");
		String username = null;
		String jwt = null;

		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer" )){
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(jwt);
		}

		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
			UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
			if(jwtUtil.validateTokens(jwt, userDetails)){
				/*
				 * since I am manually doing the authentication, I must do the steps that Spring Security is doing by default.
				 * those steps are creating the username and password token and setting it in the security context
				 */
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(paramHttpServletRequest));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		paramFilterChain.doFilter(paramHttpServletRequest, paramHttpServletResponse);
	}

}
