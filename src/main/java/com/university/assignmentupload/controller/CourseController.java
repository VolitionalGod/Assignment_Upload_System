package com.university.assignmentupload.controller;

import com.university.assignmentupload.dto.CourseDto;
import com.university.assignmentupload.dto.CoursePostDto;
import com.university.assignmentupload.service.CourseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/add-course")
    public String getAddCourse(CoursePostDto coursePostDto){
        return "add-course";
    }

    @GetMapping("/list-course")
    public String getListCourse(Model model){
        model.addAttribute("courseList", courseService.getCourses());
        return "list-course";
    }

    @PostMapping("/add-course")
    public String postAddCourse(@Valid CoursePostDto coursePostDto, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "add-course";
        }
        CourseDto postDto =  courseService.saveCourse(coursePostDto);
        return "redirect:/list-course";
    }

    @GetMapping("/edit-course")
    public String getAddCourse(@RequestParam("id") long id, Model model){
        CourseDto courseDto = this.courseService.getCourseById(id);
        model.addAttribute("courseDto", courseDto);
        return "edit-course";
    }

    @PostMapping("/edit-course")
    public String postEditSubject(@Valid CourseDto courseDto, BindingResult result, Model model){
        if (result.hasErrors()) {

            model.addAttribute("courseDto", courseDto);
            return "edit-course";
        }
        this.courseService.updateCourse(courseDto);
        return "redirect:/list-course";
    }
}
