package com.university.assignmentupload.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ID")
    private Long courseId;

    @Column(name = "COURSE_CODE")
    private String courseCode;

    @Column(name = "COURSE_DESC")
    private String courseDesc;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "LAST_MODIFIED_DATE")
    private LocalDateTime lastModifiedDate;

}
