package com.leukanz.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.leukanz.domain.Users;

public interface UsersRepository extends MongoRepository<Users, String> {
	Users findByUsername (String username);
	Users save(Users user);
	
}
