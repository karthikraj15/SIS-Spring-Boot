package com.sis.StudentInfoSystem.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sis.StudentInfoSystem.Models.Student;

public interface StudentsRepository extends MongoRepository<Student,String>{

}
