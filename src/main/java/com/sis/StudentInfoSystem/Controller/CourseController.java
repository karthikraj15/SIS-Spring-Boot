package com.sis.StudentInfoSystem.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sis.StudentInfoSystem.Models.Course;
import com.sis.StudentInfoSystem.Services.CourseServices;

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
	
	@DeleteMapping("/courses/{courseId}")
	public String deleteCourse(@PathVariable String courseId) {
		return this.courseServ.deleteCourse(courseId);
	}
	
	@PostMapping("/courses")
	public Course addCourse(@RequestBody Course cur) {
		return this.courseServ.addCourse(cur);
	}
	
	@PutMapping("/courses/{courseId}")
	public Course updateCourse(@PathVariable String courseId, @RequestBody Course course) {
		return this.courseServ.updateCourse(courseId, course);
	}
}
