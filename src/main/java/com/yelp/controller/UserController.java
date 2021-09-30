package com.yelp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yelp.models.User;
import com.yelp.repository.GroupRepo;
import com.yelp.service.UsersDetailServ;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UsersDetailServ userDetailsService;
	
	@Autowired
	GroupRepo groupRepo;
	
	
	@PostMapping("/join/{groupId}")
	public <T> HttpStatus joinGroup(@PathVariable(name = "groupId") long groupId, Principal principal){
		User user = (User)userDetailsService.loadUserByUsername(principal.getName());
		try {
			userDetailsService.joinGroup(user, groupId);
		}
		catch(IllegalArgumentException e) {
			return HttpStatus.NOT_ACCEPTABLE;
		}
		return HttpStatus.ACCEPTED;
	}
}
