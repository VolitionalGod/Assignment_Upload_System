package com.university.assignmentupload.dao;

import com.university.assignmentupload.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class HelperRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;


    public HelperRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String findRoleByUsername() {

        SimpleGrantedAuthority Role;
        String s_Role = new String();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        String sql = " SELECT ur.ROLE_ID FROM USER_ROLE ur " +
                     " INNER JOIN USER_LOGIN_DETAILS uld " +
                     " ON ur.USER_ID = uld.USER_ID " +
                     " WHERE uld.LOGIN_NAME = ? ";

        s_Role = jdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);

        //Role = new SimpleGrantedAuthority(s_Role);

        //System.out.println("Username in class: "+username);
        //System.out.println("Role in class: "+s_Role);

        return s_Role;
    }

}
