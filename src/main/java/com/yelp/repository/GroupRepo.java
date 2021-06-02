package com.yelp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yelp.models.Group;

/**interface for the jpa 
 * repository.
 * The jpa dependency creates
 * an implentation for the interface
 */
public interface GroupRepo extends JpaRepository<Group, Long>{
	

}
