package com.sis.StudentInfoSystem.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sis.StudentInfoSystem.Models.Course;
import com.sis.StudentInfoSystem.Repository.CoursesRepository;


@Service
public class CourseServices {
	
	@Autowired
	private CoursesRepository courseRepo;
	
	public List<Course> getCourses() {
		return courseRepo.findAll();
	}
	
	public Optional<Course> getCourse(String courseId) {
		return courseRepo.findById(courseId);
	}

	public Course addCourse(Course cur) {
		return courseRepo.save(cur);
	}

	public ResponseEntity<?> updateCourse(String courseId, Course course) {
		if(courseRepo.existsById(courseId)){
			courseRepo.save(course);
			return ResponseEntity.ok(course);
		}
		else {
			return ResponseEntity.status(404).body(courseId + " does not exist");
		}
	}

	public ResponseEntity<?> deleteCourse(String courseId) {
		if(courseRepo.existsById(courseId)) {
			courseRepo.deleteById(courseId);
			return ResponseEntity.ok(courseId + " deleted successfully");
		}
		else
			return ResponseEntity.status(404).body(courseId + " does not exist");
	}

}
