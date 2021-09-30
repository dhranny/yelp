package com.yelp.repository;

import org.springframework.data.repository.CrudRepository;
import com.yelp.models.User;

public interface UserRepo extends CrudRepository<User, Long>{

	User findByUsername(String username);
}
