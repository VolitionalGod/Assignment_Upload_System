package com.university.assignmentupload.dao;

import com.university.assignmentupload.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface
UserRepository  extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findAllByEnabled(Boolean active);

    /*SimpleGrantedAuthority findRoleByUsername() {

        SimpleGrantedAuthority ROLE;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        SELECT ur.ROLE_ID FROM USER_ROLE ur
        LEFT JOIN USER_LOGIN_DETAILS uld
        ON ur.USER_ID = uld.USER_ID
        WHERE uld.LOGIN_NAME=?

        return ROLE;
    }*/

}
