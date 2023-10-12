package com.university.assignmentupload.controller;
import com.university.assignmentupload.dao.HelperRepository;
import com.university.assignmentupload.dao.RoleRepository;
import com.university.assignmentupload.dto.*;
import com.university.assignmentupload.service.AcademicYearService;
import com.university.assignmentupload.service.CourseService;
import com.university.assignmentupload.service.SubjectsService;
import com.university.assignmentupload.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.university.assignmentupload.config.Constants.BASE_FILE_PATH;
import static com.university.assignmentupload.config.Constants.MAX_FILE_SIZE_IN_MB;
import static java.lang.Long.parseLong;

@Slf4j
@Controller
@ControllerAdvice
public class AssignmentController {

    private final CourseService courseService;

    private final UserService userService;

    private final SubjectsService subjectsService;

    private final RoleRepository roleRepository;

    private final AcademicYearService academicYearService;

    @Autowired
    HelperRepository helperRepository;

    public AssignmentController(CourseService courseService, UserService userService, SubjectsService subjectsService, RoleRepository roleRepository,
                                AcademicYearService academicYearService) {
        this.courseService = courseService;
        this.userService = userService;
        this.subjectsService = subjectsService;
        this.roleRepository = roleRepository;
        this.academicYearService = academicYearService;
    }

    @GetMapping("/upload-assignment")
    public String getUploadAssignment(AssignmentUploadPostDto assignmentUploadPostDto, Model model,
                                      @RequestParam(value = "fileEmptyErrorMessage", required = false) String fileEmptyErrorMessage,
                                      @RequestParam(value = "fileAlreadyUploadedErrorMessage", required = false) String fileAlreadyUploadedErrorMessage,
                                      @RequestParam(value = "fileUploadedSuccessMessage", required = false) String fileUploadedSuccessMessage,
                                      @RequestParam(value = "fileSizeErrorMessage", required = false) String fileSizeErrorMessage,
                                      @RequestParam(value = "fileNotPdfErrorMessage", required = false) String fileNotPdfErrorMessage ){


        //fileNotPdfErrorMessage
        //fileSizeErrorMessage
        //fileUploadedSuccessMessage

        //fileAlreadyUploadedErrorMessage
        model.addAttribute("academicYearList", academicYearService.getActiveAcademicYears());


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        //System.out.println("ROLE: "+userDetails.getAuthorities());

        List<UserDto> userList = userService.getActiveUsers();
        List<UserDto> principalList = new ArrayList<>();
        UserDto principal = new UserDto();

        int i;
        String check = null;

        for(i = 0 ; i < userList.size() ; i++) {


            check = (String)userList.get(i).getUsername();
            //System.out.println("username = " +username);
            //System.out.println("\ncheck = " +check);

            if(check.equals(username)) {

                //System.out.println("\nINSIDE.");
                //principal.setId(userList.get(i).getId());
                //principal.setUsername(userList.get(i).getUsername());
                principalList.add(userList.get(i));
                break;
            }
        }

        model.addAttribute("userList", principalList);

        model.addAttribute("courseList", courseService.getActiveCourses());
        model.addAttribute("subjectList", subjectsService.getActiveSubjects());

        model.addAttribute("roleList", roleRepository.findAll());

        model.addAttribute("assignmentUploadPostDto", new AssignmentUploadPostDto());

        //SimpleGrantedAuthority role = helperRepository.findRoleByUsername();

        if (fileEmptyErrorMessage != null) {
            model.addAttribute("fileEmptyErrorMessage", fileEmptyErrorMessage);
        }

        //fileAlreadyUploadedErrorMessage
        if (fileAlreadyUploadedErrorMessage != null) {
            model.addAttribute("fileAlreadyUploadedErrorMessage", fileAlreadyUploadedErrorMessage);
        }

        //fileUploadedSuccessMessage
        if (fileUploadedSuccessMessage != null) {
            model.addAttribute("fileUploadedSuccessMessage", fileUploadedSuccessMessage);
        }

        //fileSizeErrorMessage
        if (fileSizeErrorMessage != null) {
            model.addAttribute("fileSizeErrorMessage", fileSizeErrorMessage);
        }

        //fileNotPdfErrorMessage
        if (fileNotPdfErrorMessage != null) {
            model.addAttribute("fileNotPdfErrorMessage", fileNotPdfErrorMessage);
        }

        return "upload-assignment";
    }

    @PostMapping("/upload-assignment")
    public String postUploadAssignment(@ModelAttribute("assignmentUploadPostDto") AssignmentUploadPostDto assignmentUploadPostDto, Model model,
                                       RedirectAttributes redirectAttributes){

        MultipartFile file = assignmentUploadPostDto.getFile();
        String filePath;

        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";
        String pdf = "pdf";

        if (originalFilename != null && !originalFilename.isEmpty()) {
            int dotIndex = originalFilename.lastIndexOf('.');
            if (dotIndex > 0 && dotIndex < originalFilename.length() - 1) {
                fileExtension = originalFilename.substring(dotIndex + 1).toLowerCase();
            }
        }

        //System.out.println("fileExtension: "+fileExtension);

        if(!pdf.equals(fileExtension)) {

            String fileNotPdfErrorMessage = "Please upload a pdf file of size 10 MB or less.";
            //model.addAttribute("errorMessage", errorMessage);
            redirectAttributes.addAttribute("fileNotPdfErrorMessage", fileNotPdfErrorMessage);

            return "redirect:/upload-assignment";


        }











        //AcademicYearDto academicYearPart;
        //String courseIdPart;
        //String subjectIdPart;
        //String enrollmentNoPart;

        if(file.isEmpty()) {
            System.out.println("File is empty.");

            String fileEmptyErrorMessage = "Upload failed. File is empty.";
            //model.addAttribute("errorMessage", errorMessage);
            redirectAttributes.addAttribute("fileEmptyErrorMessage", fileEmptyErrorMessage);
        }

        else{

            long fileSizeBytes = file.getSize();

            double fileSizeKB = (double) fileSizeBytes / 1024;

            // Convert the file size to megabytes (optional)
            double fileSizeMB = fileSizeKB / 1024;

            if(fileSizeMB > MAX_FILE_SIZE_IN_MB) {

                String fileSizeErrorMessage = "File should be less than or equal to 10 MB.";
                //model.addAttribute("errorMessage", errorMessage);
                redirectAttributes.addAttribute("fileSizeErrorMessage", fileSizeErrorMessage);



            }

            else {


                //System.out.println("File uploaded succesfully.");

                String s = assignmentUploadPostDto.getAcademicYearId();
                Long l = Long.parseLong(s);
                AcademicYearDto academicYearPart = this.academicYearService.getAcademicYearById(l);
                String academicYearPartString = academicYearPart.getName();

                //courseIdPart = assignmentUploadPostDto.getCourseId();
                s = assignmentUploadPostDto.getCourseId();
                l = Long.parseLong(s);
                CourseDto courseIdPart = this.courseService.getCourseById(l);
                String courseIdPartString = courseIdPart.getCode();

                //subjectIdPart = assignmentUploadPostDto.getSubjectId();
                s = assignmentUploadPostDto.getSubjectId();
                l = Long.parseLong(s);
                SubjectsDto subjectIdPart = this.subjectsService.getSubjectById(l);
                String subjectIdPartString = subjectIdPart.getCode();

                //enrollmentNoPart = assignmentUploadPostDto.getEnrollmentNo();
                s = assignmentUploadPostDto.getEnrollmentNo();
                l = Long.parseLong(s);
                UserDto enrollmentNoPart = this.userService.getUserById(l);
                String enrollmentNoPartString = enrollmentNoPart.getUsername();

                //System.out.println("/" + academicYearPartString + "/" + courseIdPartString + "/" + subjectIdPartString + "/" + enrollmentNoPartString);
                filePath = BASE_FILE_PATH + academicYearPartString + "/" + courseIdPartString + "/" + subjectIdPartString + "/" + enrollmentNoPartString + "/";

                try {

                    File directory = new File(filePath);
                    if (!directory.exists()) {
                        directory.mkdirs();
                        String fileName = file.getOriginalFilename();
                        File destination = new File(filePath + fileName);
                        file.transferTo(destination);

                        String fileUploadedSuccessMessage = "Assignment uploaded successfully.";
                        //model.addAttribute("errorMessage", errorMessage);
                        redirectAttributes.addAttribute("fileUploadedSuccessMessage", fileUploadedSuccessMessage);

                    } else {
                        String fileAlreadyUploadedErrorMessage = "Upload failed. File is already uploaded.";
                        //model.addAttribute("errorMessage", errorMessage);
                        redirectAttributes.addAttribute("fileAlreadyUploadedErrorMessage", fileAlreadyUploadedErrorMessage);

                    }


                } catch (IOException e) {
                    e.printStackTrace();

                }
            }

        }

        return "redirect:/upload-assignment";
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleException(MaxUploadSizeExceededException ex, Model model, RedirectAttributes redirectAttributes) {

        // catch(SizeLimitExceededException e) {

        //System.out.println("In file exception.");

        String fileSizeErrorMessage = "Pdf file should be less than or equal to 10 MB.";
        //model.addAttribute("errorMessage", errorMessage);
        redirectAttributes.addAttribute("fileSizeErrorMessage", fileSizeErrorMessage);

        return "redirect:/upload-assignment";

    }



    @GetMapping("/evaluate-assignment")
    public String getEvaluateAssignment(AssignmentEvaluationPostDto assignmentEvaluationPostDto, Model model,
                                        @RequestParam(value = "fileDoesntExistErrorMessage", required = false) String fileDoesntExistErrorMessage ){


        //fileDoesntExistErrorMessage

        model.addAttribute("roleList", roleRepository.findAll());

        model.addAttribute("academicYearList", academicYearService.getActiveAcademicYears());
        model.addAttribute("courseList", courseService.getActiveCourses());
        model.addAttribute("subjectList", subjectsService.getActiveSubjects());

        if (fileDoesntExistErrorMessage != null) {
            model.addAttribute("fileDoesntExistErrorMessage", fileDoesntExistErrorMessage);
        }

        return "evaluate-assignment";
    }

    @PostMapping("/evaluate-assignment")
    public String postEvaluateAssignment(@ModelAttribute("assignmentEvaluationPostDto") AssignmentEvaluationPostDto assignmentEvaluationPostDto,
                                         Model model, RedirectAttributes redirectAttributes) {

        String s = assignmentEvaluationPostDto.getAcademicYearId();
        Long l = Long.parseLong(s);
        AcademicYearDto academicYearPart = this.academicYearService.getAcademicYearById(l);
        String academicYearPartString = academicYearPart.getName();

        //courseIdPart = assignmentUploadPostDto.getCourseId();
        s = assignmentEvaluationPostDto.getCourseId();
        l = Long.parseLong(s);
        CourseDto courseIdPart = this.courseService.getCourseById(l);
        String courseIdPartString = courseIdPart.getCode();

        //subjectIdPart = assignmentUploadPostDto.getSubjectId();
        s = assignmentEvaluationPostDto.getSubjectId();
        l = Long.parseLong(s);
        SubjectsDto subjectIdPart = this.subjectsService.getSubjectById(l);
        String subjectIdPartString = subjectIdPart.getCode();

        //System.out.println("/"+academicYearPartString+"/"+courseIdPartString+"/"+subjectIdPartString+"/");
        String filePath = BASE_FILE_PATH + academicYearPartString+"/"+courseIdPartString+"/"+subjectIdPartString+"/";

        //System.out.println("file details: "+assignmentEvaluationPostDto);

        /*
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            File file = new File(filePath);

            try {
                // Open the file or folder with the default program
                desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Desktop is not supported on this platform.");
        } */

        File file = new File(filePath);

        if (file.exists()) {

            try {
                String os = System.getProperty("os.name").toLowerCase();

                ProcessBuilder processBuilder = null;
                if (os.contains("win")) {
                    // Windows
                    processBuilder = new ProcessBuilder("explorer.exe", "/select," + filePath);
                } else if (os.contains("mac")) {
                    // macOS
                    processBuilder = new ProcessBuilder("open", "-R", filePath);
                } else if (os.contains("nix") || os.contains("nux") || os.contains("sunos")) {
                    // Linux/Unix
                    processBuilder = new ProcessBuilder("xdg-open", filePath);
                } else {
                    System.out.println("Unsupported operating system.");
                    //return;
                }

                processBuilder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } // if(file.exists()) ends

        else {

            String fileDoesntExistErrorMessage = "Assignment not uploaded.";
            //model.addAttribute("errorMessage", errorMessage);
            redirectAttributes.addAttribute("fileDoesntExistErrorMessage", fileDoesntExistErrorMessage);

        }


        return "redirect:/evaluate-assignment";
    }



}
