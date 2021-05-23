package com.yelp.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Arrays;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Data
public class User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private byte[] userPic;
	@Id
	long id;
	private String password;
	private String username;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
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
