package com.university.assignmentupload.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AssignmentEvaluationPostDto {

    @NotBlank(message = "academicYearId can't be blank")
    private String academicYearId;

    @NotBlank(message = "courseId can't be blank")
    private String courseId;

    @NotBlank(message = "subjectId can't be blank")
    private String subjectId;
}
