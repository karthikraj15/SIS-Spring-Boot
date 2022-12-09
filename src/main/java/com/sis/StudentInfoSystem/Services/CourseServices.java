package com.sis.StudentInfoSystem.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.sis.StudentInfoSystem.Models.Course;


public interface CourseServices {
	
	public List<Course> getCourses();
	
	public Optional<Course> getCourse(String courseId);
	
	public Course addCourse(Course courser);

	public ResponseEntity<?> updateCourse(String courseId, Course course);
	
	public ResponseEntity<?> deleteCourse(String courseId);

}
