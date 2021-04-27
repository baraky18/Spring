package com.security.SpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.security.SpringSecurity.model.AuthenticationRequest;
import com.security.SpringSecurity.model.AuthenticationResponse;
import com.security.SpringSecurity.service.MyUserDetailsService;
import com.security.SpringSecurity.util.JwtUtil;

@RestController
public class HomeController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/")
	public String home(){
		return "<h1>Welcome</h1>";
	}
	
	@GetMapping("/user")
	public String homeUser(){
		return "<h1>Welcome User</h1>";
	}
	
	@GetMapping("/admin")
	public String homeAdmin(){
		return "<h1>Welcome Admin</h1>";
	}
	
	/*
	 * since Spring security is authenticating every end point, it's not necessary to do that here, since this end point already authenticate.
	 * so we need to tell Spring security to NOT authenticate this end point. we do that by adding a method in SecurityConfiguration class
	 */
	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try{
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException e){
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
