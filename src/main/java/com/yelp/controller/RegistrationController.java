package com.yelp.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import com.yelp.repository.UserRepo;
import com.yelp.service.UsersDetailServ;
import com.sun.istack.NotNull;
import com.yelp.models.User;

@Slf4j
@RestController
@RequestMapping(path="/register")
public class RegistrationController {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UsersDetailServ userDetailsServ;

	@PostMapping
	public ResponseEntity<Object> processRegistration(@RequestBody User user) {
		log.info("User registration request received");
		try {
			user = userDetailsServ.newUser(user);
		}
		catch(BadCredentialsException e) {
			return ResponseEntity.badRequest().body("Username is already in use");
		}
		final var fUser = user;
		return ResponseEntity.ok("" + user.getUserId());
	}
}