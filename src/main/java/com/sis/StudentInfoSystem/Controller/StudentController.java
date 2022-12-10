package com.sis.StudentInfoSystem.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sis.StudentInfoSystem.Models.Student;
import com.sis.StudentInfoSystem.Services.StudentServices;

@RestController
public class StudentController {

	@Autowired
	private StudentServices studentServ;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/students")
	public List<Student> getStudents(){
		return this.studentServ.getStudents();
	}
	
	@GetMapping("/students/{usn}")
	public Optional<Student> getStudent(@PathVariable String usn) {
		return this.studentServ.getStudent(usn);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/students")
	public Student addStudent(@RequestBody Student stu) {
		return this.studentServ.addStudent(stu);
	}
	
	@PutMapping("/students/{usn}")
	public ResponseEntity<?> updateStudent(@PathVariable String usn, @RequestBody Student student) {
		return this.studentServ.updateStudent(usn, student);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/students/{usn}")
	public ResponseEntity<?> deleteStudent(@PathVariable String usn) {
		return this.studentServ.deleteStudent(usn);
	}
	
	
}