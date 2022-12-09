package com.sis.StudentInfoSystem.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="courses")
public class Course {
	
	@Id
	private String id;
	private String name;
	private String startDate;
	private String endDate;
	private Integer credit;
	private Integer total_hours;
	private String description;
	
	public Course() {
		super();
	}
	public Course(String id, String name, String startDate, String endDate, Integer credit, Integer total_hours,
			String description) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.credit = credit;
		this.total_hours = total_hours;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", credit=" + credit + ", total_hours=" + total_hours + ", description=" + description + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	public Integer getTotal_hours() {
		return total_hours;
	}
	public void setTotal_hours(Integer total_hours) {
		this.total_hours = total_hours;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

