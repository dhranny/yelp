package com.yelp.models;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
public class Topic {

	@Id
	private final long id;
	
	@OneToMany(targetEntity = User.class)
	private ArrayList<User> topicFollowers;
	
	@Valid
	private String topicName;
	
	private String topicDesc;
}
