package com.university.assignmentupload.service;

import com.university.assignmentupload.dto.AcademicYearDto;
import com.university.assignmentupload.dto.AcademicYearPostDto;
import java.util.List;

public interface AcademicYearService {

    List<AcademicYearDto> getAcademicYears();

    List<AcademicYearDto> getActiveAcademicYears();

    AcademicYearDto getAcademicYearById(Long id);

    AcademicYearDto saveAcademicYear(AcademicYearPostDto academicYearPostDto);

    void updateAcademicYear(AcademicYearDto academicYearDto);

}
