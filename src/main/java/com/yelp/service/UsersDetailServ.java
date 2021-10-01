package com.yelp.service;

import java.util.Iterator;
import java.util.Optional;

import javax.persistence.Transient;

import com.yelp.models.Group;
import com.yelp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import com.yelp.repository.GroupRepo;
import com.yelp.repository.UserRepo;

@Slf4j
@Service
public class UsersDetailServ implements UserDetailsService{

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	GroupRepo groupRepo;
	
	public UserDetails loadUserByUsername(String username) {
		UserDetails user = userRepo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("The username was not found");
		}
		return user;
	}
	

	public void joinGroup(User user, long groupId) {
		
		//if(isGroupMember(user, groupId))
			//throw new IllegalArgumentException("Already a member of this group");
		Optional<Group> group = groupRepo.findById(groupId);
		group.get().getGroupMembers();
			
		if(group.isEmpty())
			throw new IllegalArgumentException("Group does not exist");
		addToGroup(user, group.get());
	}
	
	private boolean isGroupMember(User user, long groupId) {
		if(user.getUserGroups().size() < 1) {
			return false;
		}
		for(Group group: user.getUserGroups()) {
			if(group.getGroupid() == groupId)
				return true;
		}
		return false;
	}
	
	private void addToGroup(User user, Group group) {
		user.addGroup(group);
		group.addUser(user);
	}
	
	public User loadPrincipalUser() {
		String userName = SecurityContextHolder.getContext()
				.getAuthentication()
				.getName();
		User user = (User)loadUserByUsername(userName);
		System.out.println(user.getUserId() + " Check not null");
		return user;
	}
	
	public User newUser(UserDetails user) {
		if(userRepo.findByUsername(user.getUsername()) != null)
			throw new BadCredentialsException("Username is already in use");
		System.out.println(user.getUsername());
		return userRepo.save((User)user);
	}
}
