package com.university.assignmentupload.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;
@Data
@Entity
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    private long id;

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

}