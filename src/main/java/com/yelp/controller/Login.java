package com.yelp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.yelp.models.Auth;
import com.yelp.models.AuthToken;
import com.yelp.service.JwtUtil;
import com.yelp.service.UsersDetailServ;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class Login {
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	UsersDetailServ userDetailsService;

	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public	ResponseEntity<?> login(@RequestBody Auth authRequest) throws Exception{
		log.info("Login for " + authRequest.getUsername() + authRequest.getPassword() + " has been received");
		UserDetails user = userDetailsService.loadUserByUsername(authRequest.getUsername());
		log.info(authRequest.getPassword() + user.getPassword());
		try {
			authManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")))
			);
		}
		catch(BadCredentialsException e) {
			e.printStackTrace();
			ResponseEntity.badRequest().body("Username or password is incorrect");
			
		}
		AuthToken token = new AuthToken();
		token.setToken(jwtUtil.makeToken(user));
		return ResponseEntity.ok(token);
		
	}
	
}