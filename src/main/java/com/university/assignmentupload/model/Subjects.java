package com.university.assignmentupload.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "subjects")
public class Subjects {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "SUBJECT_ID")
    private Long subjectId;

    @Column(name = "SUBJECT_CODE")
    private String subjectCode;

    @Column(name = "SUBJECT_DESC")
    private String subjectDesc;

    @Column(name = "ACTIVE")
    private boolean active;

    @Column(name = "LAST_MODIFIED_DATE")
    private LocalDateTime lastModifiedDate;

}
