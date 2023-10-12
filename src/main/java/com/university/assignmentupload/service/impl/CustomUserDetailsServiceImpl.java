package com.university.assignmentupload.service.impl;

import com.university.assignmentupload.dao.HelperRepository;
import com.university.assignmentupload.dao.UserRepository;
import com.university.assignmentupload.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@Slf4j
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //@Autowired
    //HelperRepository helperRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    public CustomUserDetailsServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        log.info("User present : {}",username);
        User userDetails = optionalUser.get();
        /*if(userDetails.getUsername().equals("admin")){
            userDetails.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }else{
            userDetails.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        }*/

        //String role = helperRepository.findRoleByUsername();
        SimpleGrantedAuthority Role;
        String s_Role = new String();

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //userDetails = (UserDetails) authentication.getPrincipal();
        //String username = userDetails.getUsername();

        String sql = " SELECT ur.ROLE_ID FROM USER_ROLE ur " +
                " INNER JOIN USER_LOGIN_DETAILS uld " +
                " ON ur.USER_ID = uld.USER_ID " +
                " WHERE uld.LOGIN_NAME = ? ";

        s_Role = jdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);






        userDetails.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority(s_Role)));

        //System.out.println("Username in userDetails: "+username);
        //System.out.println("Role in userDetails: "+s_Role);
        log.info("Data sent");
        return userDetails;
    }
}