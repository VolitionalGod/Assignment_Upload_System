package com.university.assignmentupload.controller;

import com.university.assignmentupload.dao.RoleRepository;
import com.university.assignmentupload.dto.UserDto;
import com.university.assignmentupload.dto.UserPostDto;
import com.university.assignmentupload.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.ArrayList;

//import static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List;

@Slf4j
@Controller
public class UserController {

    private final UserService userService;

    private final RoleRepository roleRepository;

    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/list-user")
    public String getUserList(Model model){
        model.addAttribute("userList", userService.getUsers());
        log.info("List --- {}", userService.getUsers());
        return "list-user";
    }

    @GetMapping("/add-user")
    public String getAddUser(UserPostDto userPostDto, Model model,
                             @RequestParam(value = "duplicateUsernameErrorMessage", required = false) String duplicateUsernameErrorMessage){
        model.addAttribute("roleList", roleRepository.findAll());

        if (duplicateUsernameErrorMessage != null) {
            model.addAttribute("duplicateUsernameErrorMessage", duplicateUsernameErrorMessage);
        }

        return "add-user";
    }

    @PostMapping("/add-user")
    public String postAddUser(@Valid  UserPostDto userPostDto, BindingResult result, Model model, RedirectAttributes redirectAttributes){
/*        if (result.hasErrors()) {
            model.addAttribute("userPostDto", userPostDto);
            return "add-user";
        }*/

        List<UserDto> currentUserList = this.userService.getUsers();

        int i;

        for(i=0;i<currentUserList.size();i++) {
            if(currentUserList.get(i).getUsername().equalsIgnoreCase(userPostDto.getUsername())) {

                // duplicate username
                String duplicateUsernameErrorMessage = "Username already taken. Try a different one.";
                //model.addAttribute("errorMessage", errorMessage);
                redirectAttributes.addAttribute("duplicateUsernameErrorMessage", duplicateUsernameErrorMessage);

                return "redirect:/add-user";

            }
        }


        UserDto postDto =  userService.saveUser(userPostDto);
        return "redirect:/list-user";
    }

    @GetMapping("/edit-user")
    public String getEditUser(@RequestParam("id") long id, Model model){
        UserDto userDto = this.userService.getUserById(id);
        model.addAttribute("userDto", userDto);
        model.addAttribute("roleList", roleRepository.findAll());
        return "edit-user";
    }

    @PostMapping("/edit-user")
    public String postEditUser(@Valid UserDto userDto, BindingResult result, Model model){
        log.info("User Update --------------- {}", userDto);
/*        if (result.hasErrors()) {

            model.addAttribute("userDto", userDto);
            return "edit-user";
        }*/
        this.userService.updateUser(userDto);
        return "redirect:/list-user";
    }

}
