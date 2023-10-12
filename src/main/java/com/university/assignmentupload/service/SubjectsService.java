package com.university.assignmentupload.service;

import com.university.assignmentupload.dto.SubjectsDto;
import com.university.assignmentupload.dto.SubjectsPostDto;
import java.util.List;

public interface SubjectsService {
    void updateSubjects(SubjectsDto programmesDto);

    List<SubjectsDto> getSubjects();

    SubjectsDto saveSubjects(SubjectsPostDto coursePostDto);

    SubjectsDto getSubjectById(Long id);

    List<SubjectsDto> getActiveSubjects();
}
