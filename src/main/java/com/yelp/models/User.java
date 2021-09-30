package com.yelp.models;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.Arrays;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.yelp.repository.GroupRepo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "user")
@Data
@NoArgsConstructor
@Component
public class User implements UserDetails{

	private static final long serialVersionUID = 1L;

	private byte[] userPic;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long userId;
	private String password;
	private String username;
	private String street;
	private String city;
	private String state;
	private String zip;
	private Date lastTimePostWasCollected;
	private String phoneNumber;
	
	@OneToMany(mappedBy = "user")
	Set<UsersPost> userPosts = new HashSet<UsersPost>();
	
	@ManyToMany(mappedBy = "groupMembers")
	@Getter(AccessLevel.NONE)
	private Set<Group> userGroups = new HashSet<Group>();
	
	public void addGroup(Group group) {
		userGroups.add(group);
	}
	
	public Set<Group> getUserGroups() {
		Set<Group> userGroups = new HashSet<Group>();
		for (Group group: this.userGroups) {
			userGroups.add(group);
		}
		return userGroups;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}		
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
