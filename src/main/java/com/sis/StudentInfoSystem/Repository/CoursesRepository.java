package com.sis.StudentInfoSystem.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sis.StudentInfoSystem.Models.Course;

public interface CoursesRepository extends MongoRepository<Course, String> {

}
