package com.university.assignmentupload.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "email_validation_status")
public class EmailValidationStatus {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "EMAIL_VALIDATION_STATUS_ID")
    private Long emailValidationStatusId;

    @Column(name = "EMAIL_VALIDATION_STATUS")
    private String emailValidationStatusDesc;

    @Column(name = "DESCRIPTION")
    private String description;

}
