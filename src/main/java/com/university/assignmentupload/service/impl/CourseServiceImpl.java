package com.university.assignmentupload.service.impl;

import com.university.assignmentupload.dao.CourseRepository;
import com.university.assignmentupload.dto.CourseDto;
import com.university.assignmentupload.dto.CoursePostDto;
import com.university.assignmentupload.exception.ResourceNotFoundException;
import com.university.assignmentupload.mapper.CourseMapper;
import com.university.assignmentupload.model.Course;
import com.university.assignmentupload.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public List<CourseDto> getCourses() {
        List<Course> course = this.courseRepository.findAll();
        return courseMapper.courseToCoursePost(course);
    }

    @Override
    public List<CourseDto> getActiveCourses() {
        List<Course> course = this.courseRepository.findAllByActive(Boolean.TRUE);
        return courseMapper.courseToCoursePost(course);
    }

    @Override
    public CourseDto getCourseById(Long id) {
        Optional<Course> optional = this.courseRepository.findById(id);
        return optional.map(courseMapper::courseToCoursePost)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CourseDto saveCourse(CoursePostDto coursePostDto) {
        Course course = courseMapper.coursePostDtoToCourse(coursePostDto);
        log.info("Course Model : {}", course);
        course = courseRepository.save(course);
        return courseMapper.courseToCoursePost(course);
    }

    @Override
    public void updateCourse(CourseDto courseDto) {
        Course course = courseMapper.courseDtoToCourse(courseDto);
        courseRepository.save(course);
    }
}
