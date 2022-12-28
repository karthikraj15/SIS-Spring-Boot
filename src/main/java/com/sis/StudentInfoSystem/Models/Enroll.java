package com.sis.StudentInfoSystem.Models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="enrolls")
public class Enroll {

	@Id
	private String usn;
	private String email;
	private List<EnrollCourse> courses;
	
	public Enroll(String usn, String email, List<EnrollCourse> courses) {
		super();
		this.usn = usn;
		this.email = email;
		this.courses = courses;
	}

	public String getUsn() {
		return usn;
	}

	public void setUsn(String usn) {
		this.usn = usn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<EnrollCourse> getCourses() {
		return courses;
	}

	public void setCourses(List<EnrollCourse> courses) {
		this.courses = courses;
	}
	
	
		
}
