package com.university.assignmentupload.controller;

import com.university.assignmentupload.dto.SubjectsDto;
import com.university.assignmentupload.dto.SubjectsPostDto;
import com.university.assignmentupload.service.SubjectsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class SubjectController {

    private final SubjectsService subjectsService;

    public SubjectController(SubjectsService subjectsService) {
        this.subjectsService = subjectsService;
    }

    @GetMapping("/add-subject")
    public String getAddSubject(SubjectsPostDto subjectsPostDto){
        return "add-subject";
    }

    @GetMapping("/list-subject")
    public String getListSubject(Model model){
        model.addAttribute("subjectList", subjectsService.getSubjects());
        return "list-subject";
    }

    @PostMapping("/add-subject")
    public String postAddSubject(@Valid SubjectsPostDto subjectsPostDto, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "add-subject";
        }
        SubjectsDto postDto =  subjectsService.saveSubjects(subjectsPostDto);
        return "redirect:/list-subject";
    }

    @GetMapping("/edit-subject")
    public String getAddSubject(@RequestParam("id") long id, Model model){
        SubjectsDto subjectsDto = this.subjectsService.getSubjectById(id);
        model.addAttribute("subjectsDto", subjectsDto);
        return "edit-subject";
    }


    @PostMapping("/edit-subject")
    public String postEditSubject(@Valid SubjectsDto subjectsDto, BindingResult result, Model model){
        if (result.hasErrors()) {

            model.addAttribute("subjectsDto", subjectsDto);
            return "edit-subject";
        }
        this.subjectsService.updateSubjects(subjectsDto);
        return "redirect:/list-subject";
    }


}
