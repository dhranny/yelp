package com.yelp.service;

import java.util.List;
import java.util.Set;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.yelp.models.User;
import com.yelp.models.UsersPost;
import com.yelp.repository.UsersPostRepo;
import org.springframework.stereotype.Service;
@Service
public class PostManager {
	@Autowired
	@Transient
	GroupManager gManager;
	
	@Autowired
	@Transient
	UsersDetailServ userDetailsServ;
	
	@Autowired
	UsersPostRepo postRepo;
	
	public long newPost(UsersPost post, long groupId) {
		setGroup(groupId, post);
		setUser(post);
		UsersPost newPost = postRepo.save(post);
		
		return newPost.getId();
	}
	
	public void setGroup(long groupId, UsersPost post) {
		post.setGroup(gManager.getGroup(groupId));
	}

	public void setUser(UsersPost post) {
		post.setUser((User)userDetailsServ.loadPrincipalUser());
	}
	
	public Iterable<UsersPost> getPosts(){
		return (Iterable<UsersPost>)postRepo.findAll();
	}
}
