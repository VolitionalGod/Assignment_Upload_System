package com.university.assignmentupload.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "acadamic_year")
public class AcademicYear {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "YEAR_ID")
    private Long yearId;

    @Column(name = "YEAR_NAME")
    private String yearName;

    @Column(name = "YEAR_START_DATE")
    private LocalDate yearStartDate;

    @Column(name = "YEAR_END_DATE")
    private LocalDate yearEndDate;

    @Column(name = "ACTIVE")
    private boolean active;

    @Column(name = "LAST_MODIFIED_DATE")
    private LocalDateTime lastModifiedDate;
}
