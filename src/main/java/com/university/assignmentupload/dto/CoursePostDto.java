package com.university.assignmentupload.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CoursePostDto {

    @NotBlank(message = "Code can't be blank")
    private String code;
    @NotBlank(message = "Desc can't be blank")
    private String desc;

    @NotNull
    private Boolean enabled;
}
