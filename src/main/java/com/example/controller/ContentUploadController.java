package com.example.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.slf4j.Slf4j;

import com.yelp.models.UsersPost;
import com.yelp.repository.GroupRepo;
import com.yelp.repository.UsersPostRepo;

@RestController
@Slf4j
@RequestMapping("contentupload/")
public class ContentUploadController {
	
	UsersPostRepo postRepo;

	@Autowired
	ContentUploadController(UsersPostRepo postRepo, GroupRepo groupRepo){
		this.postRepo = postRepo;
	}
	
	@PostMapping(consumes="application/json")
	public HttpStatus postUpdate(UsersPost userPost ){
		userPost.getGroupId();
		try {
			postRepo.save(userPost);
		}
		catch(IllegalArgumentException e) {
			log.info("POst request with id" + userPost.getId() + "failed");
			return HttpStatus.BAD_REQUEST;
		}
		return HttpStatus.ACCEPTED;
	}
	
	@DeleteMapping("/{postId}")
	public HttpStatus deletePost(@PathVariable("postId") Long postId) {
		postRepo.deleteById(postId);
		return HttpStatus.OK;
	}
	
}
