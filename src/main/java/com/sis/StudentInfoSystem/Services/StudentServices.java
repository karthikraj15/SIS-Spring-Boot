package com.sis.StudentInfoSystem.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sis.StudentInfoSystem.Models.Student;
import com.sis.StudentInfoSystem.Models.User;
import com.sis.StudentInfoSystem.Repository.StudentsRepository;
import com.sis.StudentInfoSystem.Repository.UserRepository;


@Service
public class StudentServices {
	
	@Value("${admin}")
	private String admin;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder ;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private StudentsRepository studentRepo;
	
	public List<Student> getStudents() {
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		if(email.equals(admin))
			return studentRepo.findAll();
		else {
			return studentRepo.findByEmail(email);
		}		
	}
	
	public Optional<Student> getStudent(String usn) {
		return studentRepo.findById(usn);
	}

	public ResponseEntity<?> addStudent(Student stu) {
//		String email=stu.getEmail();
//		if(studentRepo.existsByEmail(email)) {
//			return ResponseEntity.status(400).body(email + " already exist");
//		}
//		else {
//			String Id=UUID.randomUUID().toString().split("-")[0];
//			String encryptPwd=passwordEncoder.encode(stu.getUsn());
//			User user=new User(Id,email,encryptPwd,"NORMAL");
//			userRepo.save(user);
//			return ResponseEntity.ok(studentRepo.save(stu));
//		}	
		
		String Id=UUID.randomUUID().toString().split("-")[0];
		String encryptPwd=passwordEncoder.encode(stu.getUsn());
		User user= new User(Id,stu.getEmail(),encryptPwd,"STUDENT");
		studentRepo.save(stu);
		userRepo.save(user);
		return ResponseEntity.ok(stu);		
	}

	public ResponseEntity<?> updateStudent(String usn, Student student) {
		if(studentRepo.existsById(usn)) {
			studentRepo.save(student);
			return ResponseEntity.ok(student);
		}
		else {
			return ResponseEntity.status(500).body(usn + " does not exist");
		}
	}

	public ResponseEntity<?> deleteStudent(String usn) {
		if(studentRepo.existsById(usn)) {
			Optional<Student> stu= studentRepo.findById(usn);
			userRepo.deleteByEmail(stu.get().getEmail());
			studentRepo.deleteById(usn);
			return ResponseEntity.ok(usn + " deleted successfully");
		}
		else
			return ResponseEntity.status(500).body(usn + " does not exist");
	}
}
