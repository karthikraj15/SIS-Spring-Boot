package com.sis.StudentInfoSystem.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="students")
public class Student {
	
	@Id
	private String usn;
	private String name;
	private Integer sem;
	private String dob;
	private String email;
	private String phone;
	private String college;
	private String address;
	
	public Student(String usn, String name, Integer sem, String dob, String email, String phone, String college,
			String address) {
		super();
		this.usn = usn;
		this.name = name;
		this.sem = sem;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
		this.college = college;
		this.address = address;
	}
	
	public Student() {
		super();
	}
	
	@Override
	public String toString() {
		return "Student [usn=" + usn + ", name=" + name + ", sem=" + sem + ", dob=" + dob + ", email=" + email
				+ ", phone=" + phone + ", college=" + college + ", address=" + address + "]";
	}

	public String getUsn() {
		return usn;
	}
	public void setUsn(String usn) {
		this.usn = usn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSem() {
		return sem;
	}
	public void setSem(Integer sem) {
		this.sem = sem;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

}
