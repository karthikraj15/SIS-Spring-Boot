package com.sis.StudentInfoSystem.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sis.StudentInfoSystem.Models.Enroll;

public interface EnrollsRepository extends MongoRepository<Enroll, String> {
	
}
