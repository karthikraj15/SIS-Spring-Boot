package com.sis.StudentInfoSystem.Services;

import java.util.List;
import java.util.Optional;

import com.sis.StudentInfoSystem.Models.Course;


public interface CourseServices {
	
	public List<Course> getCourses();
	
	public Optional<Course> getCourse(String courseId);
	
	public Course addCourse(Course courser);

	public Course updateCourse(String courseId, Course course);
	
	public String deleteCourse(String courseId);

}
