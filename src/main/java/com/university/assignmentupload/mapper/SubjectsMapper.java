package com.university.assignmentupload.mapper;

import com.university.assignmentupload.dto.SubjectsDto;
import com.university.assignmentupload.dto.SubjectsPostDto;
import com.university.assignmentupload.model.Subjects;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubjectsMapper {

    @Mapping(target = "subjectDesc", source = "desc")
    @Mapping(target = "subjectCode", source = "code")
    @Mapping(target = "active", source = "enabled")
    Subjects subjectsPostDtoToSubjects(SubjectsPostDto subjectsPostDto);

    @Mapping(target = "desc", source = "subjectDesc")
    @Mapping(target = "code", source = "subjectCode")
    @Mapping(target = "enabled", source = "active")
    @Mapping(target = "id", source = "subjectId")
    SubjectsDto programmesToProgrammesPost(Subjects subjects);

    @Mapping(target = "subjectDesc", source = "desc")
    @Mapping(target = "subjectCode", source = "code")
    @Mapping(target = "active", source = "enabled")
    @Mapping(target = "subjectId", source = "id")
    Subjects subjectsDtoToSubjects(SubjectsDto programmesDto);

    List<SubjectsDto> programmesToProgrammesPost(List<Subjects> subjectsList);
}
