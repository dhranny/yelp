package com.yelp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.yelp.models.Group;

public interface GroupRepo extends CrudRepository<Group, Long>{
	
@Override
	Optional<Group> findById(Long id);
}
