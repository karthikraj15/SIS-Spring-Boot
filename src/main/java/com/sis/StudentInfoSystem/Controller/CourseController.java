package com.sis.StudentInfoSystem.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sis.StudentInfoSystem.Models.Course;
import com.sis.StudentInfoSystem.Services.CourseServices;


@CrossOrigin("*")
@RestController
public class CourseController {
	
	@Autowired
	private CourseServices courseServ;
	
	@GetMapping("/courses")
	public List<Course> getCourses(){
		return this.courseServ.getCourses();
	}
	
	@GetMapping("/courses/{courseId}")
	public Optional<Course> getCourse(@PathVariable String courseId) {
		return this.courseServ.getCourse(courseId);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/courses")
	public Course addCourse(@RequestBody Course cur) {
		return this.courseServ.addCourse(cur);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/courses/{courseId}")
	public ResponseEntity<?> updateCourse(@PathVariable String courseId, @RequestBody Course course) {
		return this.courseServ.updateCourse(courseId, course);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/courses/{courseId}")
	public ResponseEntity<?> deleteCourse(@PathVariable String courseId) {
		return this.courseServ.deleteCourse(courseId);
	}
}
