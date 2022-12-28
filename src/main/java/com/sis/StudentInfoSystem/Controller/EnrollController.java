package com.sis.StudentInfoSystem.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sis.StudentInfoSystem.Services.EnrollServices;
import com.sis.StudentInfoSystem.Models.Enroll;

@CrossOrigin("*")
@RestController
public class EnrollController {
	
	@Autowired
	private EnrollServices enrollServ;
	
	//student enrolling to a course
	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@PostMapping("/courses/{courseId}/enroll")
	public ResponseEntity<?> studentEnroll(@PathVariable String courseId) {
		return this.enrollServ.studentEnroll(courseId);
	}
	
	//all enrollments
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/enrolls")
	public List<Enroll> getEnrolls(){
		return this.enrollServ.getEnrolls();
	}
	
	//specific enrollment of student
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/enrolls/{usn}")
	public Optional<Enroll> getEnroll(@PathVariable String usn){
		return this.enrollServ.getEnroll(usn);
	}
	
	//approval of enrollment request of a student
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/enrolls/{usn}/{courseId}")
	public ResponseEntity<?> approvalEnroll(@PathVariable("usn") String usn,@PathVariable("courseId") String courseId){
		return this.enrollServ.approvalEnroll(usn,courseId);
	}
	
	//view enrolled courses of student and its status
	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@GetMapping("/enrolled-courses/{usn}")
	public ResponseEntity<?> enrolledCourses(@PathVariable String usn){
		return this.enrollServ.enrolledCourses(usn);
	}
	
	//students not enrolled to courseId
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/courses/{courseId}/assign")
	public ResponseEntity<?> viewStudents(@PathVariable String courseId){
		return this.enrollServ.viewStudents(courseId);
	}
	 
	//assign course to a student or selected students 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/courses/{courseId}/assign/{usn}")
	public ResponseEntity<?>  assignCourse(@PathVariable("courseId") String courseId,@PathVariable("usn") String usn){
		return this.enrollServ.assignCourse(courseId,usn);
	}	
}
