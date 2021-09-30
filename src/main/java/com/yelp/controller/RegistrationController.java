package com.yelp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import com.yelp.repository.UserRepo;
import com.yelp.models.User;

@Slf4j
@RestController
@RequestMapping(path="/register", produces="application/json")
public class RegistrationController {
	private UserRepo userRepo;
	
	@Autowired
	public RegistrationController(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@PostMapping()
	public HttpStatus processRegistration(User user) {
		log.info("User registration request received");
		userRepo.save(user);
		return HttpStatus.ACCEPTED;
	}
}