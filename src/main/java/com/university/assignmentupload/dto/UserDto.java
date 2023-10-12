package com.university.assignmentupload.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import static com.university.assignmentupload.config.Constants.INVALID_INPUT_FOR_ID;

@Data
public class UserDto {

    @NotNull
    @Positive(message = INVALID_INPUT_FOR_ID)
    private Long id;

    @NotNull
    private String roleId;

    @Max(value = 20)
    @NotBlank(message = "username can't be blank")
    private String username;
    @NotBlank(message = "password can't be blank")
    private String password;
    @Max(value = 20)
    @NotBlank(message = "first name can't be blank")
    private String firstName;
    @Max(value = 20)
    @NotBlank(message = "last name can't be blank")
    private String lastName;
    @Max(value = 50)
    @NotBlank(message = "email can't be blank")
    private String email;
    @NotNull
    private Boolean enabled;
}
