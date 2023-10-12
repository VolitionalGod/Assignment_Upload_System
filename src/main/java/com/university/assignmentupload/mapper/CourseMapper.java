package com.university.assignmentupload.mapper;


import com.university.assignmentupload.dto.CourseDto;
import com.university.assignmentupload.dto.CoursePostDto;
import com.university.assignmentupload.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {

    @Mapping(target = "courseCode", source = "code")
    @Mapping(target = "courseDesc", source = "desc")
    @Mapping(target = "active", source = "enabled")
    Course coursePostDtoToCourse(CoursePostDto coursePost);

    @Mapping(target = "desc", source = "courseDesc")
    @Mapping(target = "code", source = "courseCode")
    @Mapping(target = "enabled", source = "active")
    @Mapping(target = "id", source = "courseId")
    CourseDto courseToCoursePost(Course course);

    @Mapping(target = "courseDesc", source = "desc")
    @Mapping(target = "courseCode", source = "code")
    @Mapping(target = "active", source = "enabled")
    @Mapping(target = "courseId", source = "id")
    Course courseDtoToCourse(CourseDto courseDto);

    List<CourseDto> courseToCoursePost(List<Course> courses);

}
