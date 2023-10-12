package com.university.assignmentupload.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import static com.university.assignmentupload.config.Constants.INVALID_INPUT_FOR_ID;

@Data
public class CourseDto {

    @NotNull
    @Positive(message = INVALID_INPUT_FOR_ID)
    private Long id;
    @NotBlank(message = "Code can't be blank")
    private String code;
    @NotBlank(message = "Desc can't be blank")
    private String desc;
    @NotNull
    private Boolean enabled;
}

