package com.university.assignmentupload.service;

import com.university.assignmentupload.dto.CourseDto;
import com.university.assignmentupload.dto.CoursePostDto;
import java.util.List;

public interface CourseService {
    List<CourseDto> getCourses();

    List<CourseDto> getActiveCourses();

    CourseDto getCourseById(Long id);

    CourseDto saveCourse(CoursePostDto courseDto);

    void updateCourse(CourseDto courseDto);
}
