package com.yelp.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Entity
@Data
@Slf4j
public class UsersPost extends Model{
	@Id
	long id;
	long userId;
	
	byte[] postPic;
	
	String posttext;
	
	long groupId;
}
