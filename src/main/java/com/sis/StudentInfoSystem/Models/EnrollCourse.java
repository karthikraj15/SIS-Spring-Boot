package com.sis.StudentInfoSystem.Models;

public class EnrollCourse {

	private String courseId;
	private String name;
	private Boolean status;
	
	public EnrollCourse(String courseId, String name, Boolean status) {
		super();
		this.courseId = courseId;
		this.name = name;
		this.status = status;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
	
}
