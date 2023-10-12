package com.university.assignmentupload.service;

import com.university.assignmentupload.dto.UserDto;
import com.university.assignmentupload.dto.UserPostDto;
import java.util.List;

public interface UserService {

    List<UserDto> getUsers();

    List<UserDto> getActiveUsers();

    UserDto getUserById(Long id);

    UserDto saveUser(UserPostDto userDto);

    void updateUser(UserDto userDto);
}
