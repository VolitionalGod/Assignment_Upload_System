package com.university.assignmentupload.dto;

import com.university.assignmentupload.config.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class AcademicYearDto {


    @NotNull
    @Positive(message = Constants.INVALID_INPUT_FOR_ID)
    private Long id;
    @NotBlank(message = "Academic year can't be blank")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Academic year start date can't be null")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Academic year end date can't be null")
    private LocalDate endDate;

    @NotNull
    private boolean enabled;

}
