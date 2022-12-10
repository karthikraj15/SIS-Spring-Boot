package com.sis.StudentInfoSystem.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sis.StudentInfoSystem.Models.Student;
import com.sis.StudentInfoSystem.Models.User;
import com.sis.StudentInfoSystem.Repository.StudentsRepository;
import com.sis.StudentInfoSystem.Repository.UserRepository;

@Service
public class StudentServicesImpl implements StudentServices {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder ;
	
	@Autowired
	private StudentsRepository studentRepo;
	
	@Override
	public List<Student> getStudents() {
		return studentRepo.findAll();	
	}
	
	@Override
	public Optional<Student> getStudent(String usn) {
		return studentRepo.findById(usn);
	}

	@Override
	public Student addStudent(Student stu) {
		String Id=UUID.randomUUID().toString().split("-")[0];
		String encryptPwd=passwordEncoder.encode(stu.getUsn());
		User user=new User(Id,stu.getEmail(),encryptPwd,"NORMAL");
		userRepo.save(user);
		return studentRepo.save(stu);
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
			Optional<Student> stu= studentRepo.findById(usn);
			userRepo.deleteByEmail(stu.get().getEmail());
			studentRepo.deleteById(usn);
			return ResponseEntity.ok(usn + " deleted successfully");
		}
		else
			return ResponseEntity.status(404).body(usn + " does not exist");
	}
}
