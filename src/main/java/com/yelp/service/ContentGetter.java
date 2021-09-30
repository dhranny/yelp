package com.yelp.service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.yelp.models.User;
import com.yelp.models.UsersPost;
import com.yelp.repository.UsersPostRepo;

@Service
public class ContentGetter {

	UsersPostRepo postRepo;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	ContentGetter(UsersPostRepo postRepo){
		this.postRepo = postRepo;
	}
	
	public List<UsersPost> getPosts(Principal principal){
		String username = principal.getName();
		User user = (User)userDetailsService.loadUserByUsername(username);
		Date lastUpdateTime = user.getLastTimePostWasCollected();
		if(lastUpdateTime == null) {
			return (List<UsersPost>) postRepo.findAll();
		}
		List<UsersPost> newUserPosts = postRepo.findLatestPost(lastUpdateTime);
		return newUserPosts;
	}
	
}
