package com.university.assignmentupload.service.impl;

import com.university.assignmentupload.dao.UserRepository;
import com.university.assignmentupload.dto.UserDto;
import com.university.assignmentupload.dto.UserPostDto;
import com.university.assignmentupload.exception.ResourceNotFoundException;
import com.university.assignmentupload.mapper.UserMapper;
import com.university.assignmentupload.model.User;
import com.university.assignmentupload.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> userList = this.userRepository.findAll();
        return this.userMapper.usersListToUserDtoList(userList);
    }

    @Override
    public List<UserDto> getActiveUsers() {
        List<User> userList = userRepository.findAllByEnabled(Boolean.TRUE);
        return userMapper.usersListToUserDtoList(userList);
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optional = this.userRepository.findById(id);
        return optional.map(userMapper::userToUserDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public UserDto saveUser(UserPostDto userDto) {
        User user = userMapper.userPostDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setExpiryDate(LocalDateTime.now().plusDays(90));
        user = this.userRepository.save(user);
        return this.userMapper.userToUserDto(user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        user.setExpiryDate(userRepository.findByUsername(userDto.getUsername()).get().getExpiryDate());
        this.userRepository.save(user);
     }
}
