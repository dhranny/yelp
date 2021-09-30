package com.yelp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.yelp.models.UsersPost;
import com.yelp.service.PostManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@RestController
public class UserPostController {
	
	@Autowired
	PostManager pManager;

	@PostMapping(consumes = "application/json", path = "/{groupId}")
	public ResponseEntity<String> post(@RequestBody UsersPost post, @PathVariable(name = "groupId")long groupId){
		String postId = "" + pManager.newPost(post, groupId);
		ResponseEntity<String> resEntity = new ResponseEntity<>(postId, HttpStatus.CREATED); 
		return resEntity;
	}
	
}
