package com.university.assignmentupload.mapper;


import com.university.assignmentupload.dto.AcademicYearDto;
import com.university.assignmentupload.dto.AcademicYearPostDto;
import com.university.assignmentupload.model.AcademicYear;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AcademicYearMapper {

    @Mapping(target = "yearName", source = "name")
    @Mapping(target = "yearStartDate", source = "startDate")
    @Mapping(target = "yearEndDate", source = "endDate")
    @Mapping(target = "active", source = "enabled")
    AcademicYear academicYearPostDtoToAcademicYear(AcademicYearPostDto academicYearPostDto);

    @Mapping(target = "id", source = "yearId")
    @Mapping(target = "name", source = "yearName")
    @Mapping(target = "enabled", source = "active")
    @Mapping(target = "startDate", source = "yearStartDate")
    @Mapping(target = "endDate", source = "yearEndDate")
    AcademicYearDto academicYearToAcademicYearDto(AcademicYear academicYear);

    @Mapping(target = "yearId", source = "id")
    @Mapping(target = "yearName", source = "name")
    @Mapping(target = "active", source = "enabled")
    @Mapping(target = "yearStartDate", source = "startDate")
    @Mapping(target = "yearEndDate", source = "endDate")
    AcademicYear academicYearDtoToAcademicYear(AcademicYearDto academicYearDto);

    List<AcademicYearDto> academicYearsToAcademicYearDtos(List<AcademicYear> years);
}
