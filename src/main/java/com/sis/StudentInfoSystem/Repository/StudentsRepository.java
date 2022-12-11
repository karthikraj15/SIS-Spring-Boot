package com.sis.StudentInfoSystem.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sis.StudentInfoSystem.Models.Student;

public interface StudentsRepository extends MongoRepository<Student,String>{
	
	List<Student> findByEmail(String email);
	
	Boolean existsByEmail(String email);
	
}
