package com.yelp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import com.yelp.service.GroupManager;
import com.yelp.service.UsersDetailServ;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class UsersPost extends Model{
	

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	User user;
	
	Date dateTime = new Date();
	
	byte[] postPic;
	
	String posttext;
	
	@ManyToOne
	@JoinColumn(name = "groupid")
	Group group;
	
		
	
}
