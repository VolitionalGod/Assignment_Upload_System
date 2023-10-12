package com.university.assignmentupload.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AcademicYearPostDto {

    @NotBlank(message = "Academic year can't be blank")
    private String name;

    @NotNull(message = "Academic year start date can't be null")
    private LocalDate startDate;

    @NotNull(message = "Academic year end date can't be null")
    private LocalDate endDate;

    @NotNull
    private Boolean enabled;
}
