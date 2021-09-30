package com.yelp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.yelp.models.UsersPost;

public interface UsersPostRepo extends CrudRepository<UsersPost, Long>{

	@Query(value = "select * from UsersPosts where dateTime > ?1", nativeQuery = true)
	public List<UsersPost> findLatestPost(Date dateTime);
}
