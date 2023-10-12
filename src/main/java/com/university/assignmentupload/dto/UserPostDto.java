package com.university.assignmentupload.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserPostDto {

    @Min(value = 5)
    @Max(value = 20)
    @NotBlank(message = "username can't be blank")
    private String username;
    @Min(value = 8)
    @NotBlank(message = "password can't be blank")
    private String password;
    @Min(value = 2)
    @Max(value = 20)
    @NotBlank(message = "first name can't be blank")
    private String firstName;
    @Min(value = 2)
    @Max(value = 20)
    @NotBlank(message = "last name can't be blank")
    private String lastName;
    @Min(value = 3)
    @Max(value = 50)
    @NotBlank(message = "email can't be blank")
    private String email;
    @NotNull
    private Boolean enabled;
    @NotNull
    private String roleId;

}
