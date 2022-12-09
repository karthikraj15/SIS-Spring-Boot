package com.sis.StudentInfoSystem.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.sis.StudentInfoSystem.Models.Student;

public interface StudentServices {
	
	public List<Student> getStudents();
	
	public Optional<Student> getStudent(String usn);
	
	public Student addStudent(Student stu);

	public ResponseEntity<?> updateStudent(String uns, Student student);
	
	public ResponseEntity<?> deleteStudent(String usn);
}
