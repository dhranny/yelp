package com.yelp.models;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Entity(name = "user_groups")
@Data
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long groupid;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "User_Group",
		joinColumns = { @JoinColumn(name = "groupid")},
		inverseJoinColumns = {@JoinColumn(name = "userId")})
	private Set<User> groupMembers = new HashSet<User>();
	
	@NotBlank
	private String groupName;
	
	@NotBlank
	private String groupDesc;
	
	@OneToMany(mappedBy = "user")
	Set<UsersPost> userPosts = new HashSet<UsersPost>();
	
	public void addUser(User user) {
		groupMembers.add(user);
	}
}
