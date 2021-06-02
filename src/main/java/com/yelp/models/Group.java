package com.yelp.models;

import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import lombok.Data;

@Entity
@Data
public class Group {

	@Id
	private final long id;
	
	@OneToMany(targetEntity = User.class)
	private List<User> topicFollowers;
	
	@Min(3)
	@Max(10)
	private String groupName;
	
	@Min(50)
	@Max(300)
	private String groupDesc;
}
