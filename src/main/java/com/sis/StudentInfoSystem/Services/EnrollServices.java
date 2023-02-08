package com.sis.StudentInfoSystem.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sis.StudentInfoSystem.Models.Course;
import com.sis.StudentInfoSystem.Models.Enroll;
import com.sis.StudentInfoSystem.Models.EnrollCourse;
import com.sis.StudentInfoSystem.Models.Student;
import com.sis.StudentInfoSystem.Repository.CoursesRepository;
import com.sis.StudentInfoSystem.Repository.EnrollsRepository;
import com.sis.StudentInfoSystem.Repository.StudentsRepository;

@Service
public class EnrollServices {
	
	@Autowired
	private EnrollsRepository enrollRepo;
	
	@Autowired
	private CoursesRepository courseRepo;
	
	@Autowired
	private StudentsRepository studentRepo;

	public ResponseEntity<?> studentEnroll(String courseId) {
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		List<Student> stu = studentRepo.findByEmail(email);
		String usn = stu.get(0).getUsn();
		Optional<Course> course=courseRepo.findById(courseId);
		String courseName=course.get().getName();
		EnrollCourse enrollCourse = new EnrollCourse(courseId,courseName,false);
		
		if(enrollRepo.existsById(usn)) {
			Optional<Enroll> enroll = enrollRepo.findById(usn);
			List<EnrollCourse> enrolled = enroll.get().getCourses();
			
			for(EnrollCourse e:enrolled) {
				if(e.getCourseId().equals(courseId))
					return ResponseEntity.status(500).body("CourseId : "+courseId + " already enrolled");
			}
			enrolled.add(enrollCourse);
			enroll.get().setCourses(enrolled);
			return ResponseEntity.ok(enrollRepo.save(enroll.get()));
		}
		else {
			Enroll enroll=new Enroll(usn,email, List.of( enrollCourse));
			return ResponseEntity.ok(enrollRepo.save(enroll));
		}
	}
	
	public List<Enroll> getEnrolls(){
		return enrollRepo.findAll();
	}
	
	public Optional<Enroll> getEnroll(String usn) {
		return enrollRepo.findById(usn);
	}
	
	public ResponseEntity<?> approvalEnroll(String usn,String courseId){
		Optional<Enroll> enroll;
		if(enrollRepo.existsById(usn))
			enroll = enrollRepo.findById(usn);
		else
			return ResponseEntity.status(500).body( "Student : " + usn + " has not enrolled to " + courseId);
		
		List<EnrollCourse> enrolled = enroll.get().getCourses();
		for(EnrollCourse e:enrolled) {
			if(e.getCourseId().equals(courseId)) {
				e.setStatus(true);
				enroll.get().setCourses(enrolled);
				return ResponseEntity.ok(enrollRepo.save(enroll.get()));
			}
		}
		return ResponseEntity.status(500).body( "Student : " + usn + " has not enrolled to " + courseId); 
	}
	
	public ResponseEntity<?> enrolledCourses(String usn){
		Optional<Enroll> enroll = enrollRepo.findById(usn);
		return ResponseEntity.ok(enroll.get().getCourses());
	}
	
	public ResponseEntity<?> viewStudents(String courseId){
		List<Student> students = studentRepo.findAll();
		List<Enroll> enrolls = enrollRepo.findAll();
		List<String> courseIdStudents = new ArrayList<String>(); ;
		
		for(Enroll enroll:enrolls) {
			for(EnrollCourse e:enroll.getCourses()) {
				if(e.getCourseId().equals(courseId))
					courseIdStudents.add(enroll.getUsn());		
			}	
		}
		
		List<Student> NotEnrolledStudents = new ArrayList<Student>();
		for(Student stu:students) {
			if(!courseIdStudents.contains(stu.getUsn()))
				NotEnrolledStudents.add(stu);	
		}
		return ResponseEntity.ok(NotEnrolledStudents);	
	}
	
	public ResponseEntity<?> assignCourse(String courseId,String usn){
		Optional<Student> stu = studentRepo.findById(usn);
		String email=stu.get().getEmail();
		Optional<Course> course=courseRepo.findById(courseId);
		String courseName=course.get().getName();
		EnrollCourse enrollCourse = new EnrollCourse(courseId,courseName,true);
		
		if(enrollRepo.existsById(usn)) {
			Optional<Enroll> enroll = enrollRepo.findById(usn);
			List<EnrollCourse> enrolled = enroll.get().getCourses();
			
			for(EnrollCourse e:enrolled) {
				if(e.getCourseId().equals(courseId))
					return ResponseEntity.status(500).body("Student : " + usn + " has already enrolled the course : "+courseId);
			}
			enrolled.add(enrollCourse);
			enroll.get().setCourses(enrolled);
			return ResponseEntity.ok(enrollRepo.save(enroll.get()));
		}
		else {
			Enroll enroll=new Enroll(usn,email, List.of( enrollCourse));
			return ResponseEntity.ok(enrollRepo.save(enroll));
		}
	}	
}
