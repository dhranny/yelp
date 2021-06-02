package com.yelp.repository;

/**interface for the jpa 
 * repository.
 * The jpa dependency creates
 * an implentation for the interface
 */
public interface GroupRepo extends JpaRepository<Group, Long>{
	

}
