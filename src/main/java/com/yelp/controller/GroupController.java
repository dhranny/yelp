package com.yelp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.yelp.service.GroupManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import com.yelp.models.Group;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/group")
public class GroupController {

	@Autowired
	GroupManager gManager;
	
	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createGroup(@RequestBody Group group, Errors error){
		String groupId = "" + gManager.newGroup(group);
		
		ResponseEntity<String> resEntity = new ResponseEntity<>(groupId, HttpStatus.CREATED); 
		return resEntity;
	}
}
