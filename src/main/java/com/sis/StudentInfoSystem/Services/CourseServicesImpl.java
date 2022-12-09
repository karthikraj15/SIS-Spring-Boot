package com.sis.StudentInfoSystem.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sis.StudentInfoSystem.Models.Course;
import com.sis.StudentInfoSystem.Repository.CoursesRepository;

@Service
public class CourseServicesImpl implements CourseServices {

	@Autowired
	private CoursesRepository courseRepo;
	
	@Override
	public Optional<Course> getCourse(String courseId) {
		return courseRepo.findById(courseId);
	}

	@Override
	public Course addCourse(Course cur) {
		return courseRepo.save(cur);
	}

	@Override
	public List<Course> getCourses() {
		return courseRepo.findAll();
	}

	@Override
	public Course updateCourse(String courseId, Course course) {
		if(courseRepo.existsById(courseId))
			courseRepo.deleteById(courseId);
		return courseRepo.save(course);
	}

	@Override
	public String deleteCourse(String courseId) {
		if(courseRepo.existsById(courseId)) 
			courseRepo.deleteById(courseId);
		else
			return "Course : "+ courseId+" - does not exists";
		return "Course : "+ courseId+" - deleted sucessfully";
	}

}
