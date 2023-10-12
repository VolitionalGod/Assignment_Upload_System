package com.university.assignmentupload.controller;
import com.university.assignmentupload.dto.AcademicYearDto;
import com.university.assignmentupload.dto.AcademicYearPostDto;
import com.university.assignmentupload.service.AcademicYearService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AcademicYearController {

    private final AcademicYearService academicYearService;

    public AcademicYearController(AcademicYearService academicYearService) {
        this.academicYearService = academicYearService;
    }

    @GetMapping("/add-academic-year")
    public String getAddAcademicYear(AcademicYearPostDto academicYearPostDto){

        return "add-academic-year";
    }

    @GetMapping("/list-academic-year")
    public String getListAcademicYear(Model model){
        model.addAttribute("academicYearList", academicYearService.getAcademicYears());
        return "list-academic-year";
    }

    @PostMapping("/add-academic-year")
    public String postAddAcademicYear(@Valid AcademicYearPostDto academicYearPostDto, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "add-academic-year";
        }
        System.out.println(">>>> enabled:"+academicYearPostDto.getEnabled());
        AcademicYearDto academicYearDto =  academicYearService.saveAcademicYear(academicYearPostDto);
        return "redirect:/list-academic-year";
    }

    @GetMapping("/edit-academic-year")
    public String getAddAcademicYear(@RequestParam("id") long id, Model model){
        AcademicYearDto academicYearDto = this.academicYearService.getAcademicYearById(id);
        model.addAttribute("academicYearDto", academicYearDto);
        return "edit-academic-year";
    }

    @PostMapping("/edit-academic-year")
    public String postEditAcademicYear(@Valid AcademicYearDto academicYearDto, BindingResult result, Model model){
        if (result.hasErrors()) {

            model.addAttribute("academicYearDto", academicYearDto);
            return "edit-academic-year";
        }
        this.academicYearService.updateAcademicYear(academicYearDto);
        return "redirect:/list-academic-year";
    }
}
