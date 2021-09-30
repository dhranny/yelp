package com.yelp.service;

import com.yelp.models.Group;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.yelp.repository.GroupRepo;

import lombok.val;

@Service
public class GroupManager {

	@Autowired
	GroupRepo groupRepo;
	
	public long newGroup(Group group) {
		Group newGroup = groupRepo.save(group);
		return newGroup.getGroupid();
	}
	
	public Group getGroup(long groupId) {
		val opGroup = groupRepo.findById(groupId);
		if(opGroup.isPresent())
			return opGroup.get();
		throw new IllegalArgumentException("Group id is not valid");  //if we get here, the groupId does not exist
	}
}
