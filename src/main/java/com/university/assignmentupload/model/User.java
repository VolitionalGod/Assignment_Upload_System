package com.university.assignmentupload.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="user_login_details")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "LOGIN_NAME")
    private String username;
    @Column(name = "PASSCODE")
    private String password;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ENABLED")
    private boolean enabled;
    @Column(name = "NON_EXPIRED")
    private boolean accountNonExpired;
    @Column(name = "NON_LOCKED")
    private boolean accountNonLocked;
    @Column(name = "EXPIRY_DATE")
    private LocalDateTime expiryDate;
    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "USER_ID", referencedColumnName = "USER_ID"),
            inverseJoinColumns = @JoinColumn(
                    name = "ROLE_ID", referencedColumnName = "ROLE_ID"))
    private Collection<Role> roles;

}
