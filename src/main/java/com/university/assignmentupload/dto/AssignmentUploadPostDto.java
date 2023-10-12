package com.university.assignmentupload.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AssignmentUploadPostDto {


    @NotBlank(message = "academicYearId can't be blank")
    private String academicYearId;

    @NotBlank(message = "enrollmentNo can't be blank")
    private String enrollmentNo;

    @NotBlank(message = "courseId can't be blank")
    private String courseId;

    @NotBlank(message = "subjectId can't be blank")
    private String subjectId;

    private MultipartFile file;
}
