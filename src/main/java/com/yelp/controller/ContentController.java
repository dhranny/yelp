package com.yelp.controller;

import java.util.Collection;
import java.net.http.HttpResponse;
import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.slf4j.Slf4j;

import com.yelp.models.User;
import com.yelp.models.UsersPost;
import com.yelp.repository.GroupRepo;
import com.yelp.repository.UsersPostRepo;
import com.yelp.service.ContentGetter;

@RestController
@Slf4j
@RequestMapping("/contentupload/")
public class ContentController {
	
	UsersPostRepo postRepo;
	ContentGetter conGetter;

	@Autowired
	ContentController(UsersPostRepo postRepo, GroupRepo groupRepo, ContentGetter conGetter){
		this.postRepo = postRepo;
		this.conGetter = conGetter;
	}
	
	@GetMapping()
	public List<UsersPost> getPosts(Principal principal){
		return conGetter.getPosts(principal);
		
	}
	
	
	@PostMapping(consumes="application/json")
	public HttpStatus postUpdate(UsersPost userPost ){
		User user = (User) SecurityContextHolder
												.getContext()
													.getAuthentication()
														.getPrincipal();
		try {
			postRepo.save(userPost);
			log.info("Post request with id" + userPost.getId() + "success");
		}
		catch(IllegalArgumentException e) {
			log.info("Post request with id" + userPost.getId() + "failed");
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
