package com.university.assignmentupload.dto;

import com.university.assignmentupload.config.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
public class SubjectsDto {

    @NotNull
    @Positive(message = Constants.INVALID_INPUT_FOR_ID)
    private Long id;
    @NotBlank(message = "Code can't be blank")
    private String code;
    @NotBlank(message = "Desc can't be blank")
    private String desc;
    @NotNull
    private boolean enabled;
}
