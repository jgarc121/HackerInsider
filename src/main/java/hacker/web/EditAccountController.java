package hacker.web;


import hacker.User;
import hacker.data.UserRepository;
import hacker.security.RegistrationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/editAccount")
public class EditAccountController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EditAccountController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    public String editAccountInfo(Model model, @AuthenticationPrincipal User user) {

        addUserInfoToModel(model, user);
        return "editAccount";
    }

    private void addUserInfoToModel(Model model, User user) {
        model.addAttribute("fullName", user.getFullName());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("linkedin", user.getLinkedInURL());
        model.addAttribute("jobRole", user.getJobRole());
    }

    @ModelAttribute(name = "registrationForm")
    public RegistrationForm addRegistrationFormToModel() {
        return new RegistrationForm();
    }

    @PostMapping
    public String processRegistration(@Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm, Errors errors, @AuthenticationPrincipal User user) {
        if (errors.hasErrors())
            return "/editAccount";

        try {
            User updateUser = user;
            userRepo.save(registrationForm.updateUser(updateUser, passwordEncoder, registrationForm));
        } catch (DataIntegrityViolationException e) {
            errors.rejectValue("username", "invalidUsername", "Username not available. Please choose another username.");
            return "/editAccount";
        }
        return "redirect:/accountInformation";
    }


}
