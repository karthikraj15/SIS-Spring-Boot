package com.sis.StudentInfoSystem.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sis.StudentInfoSystem.Models.User;

public interface UserRepository extends MongoRepository<User,String>{
	User findByEmail(String email);
}
