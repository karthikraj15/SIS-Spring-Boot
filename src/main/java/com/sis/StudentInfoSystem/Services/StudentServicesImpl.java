package com.sis.StudentInfoSystem.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;

import com.sis.StudentInfoSystem.Models.Student;
import com.sis.StudentInfoSystem.Repository.StudentsRepository;

@Service
public class StudentServicesImpl implements StudentServices {

	@Autowired
	private StudentsRepository studentRepo;
	
	@Override
	public Optional<Student> getStudent(String usn) {
		return studentRepo.findById(usn);
	}

	@Override
	public Student addStudent(Student stu) {
		return studentRepo.save(stu);
	}

	@Override
	public List<Student> getStudents() {
		return studentRepo.findAll();
	}

	@Override
	public ResponseEntity<?> updateStudent(String usn, Student student) {
		if(studentRepo.existsById(usn)) {
			studentRepo.save(student);
			return ResponseEntity.ok(student);
		}
		else {
			return ResponseEntity.status(404).body(usn + " does not exist");
		}
	}

	@Override
	public ResponseEntity<?> deleteStudent(String usn) {
		if(studentRepo.existsById(usn)) {
			studentRepo.deleteById(usn);
			return ResponseEntity.ok(usn + " deleted successfully");
		}
		else
			return ResponseEntity.status(404).body(usn + " does not exist");
	}
}
