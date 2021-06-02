package com.yelp.repository;

import org.springframework.data.repository.CrudRepository;

import com.yelp.models.UsersPost;

public interface UsersPostRepo extends CrudRepository<UsersPost, Long>{

}
