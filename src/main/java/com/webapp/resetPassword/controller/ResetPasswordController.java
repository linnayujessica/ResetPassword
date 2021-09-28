package com.webapp.resetPassword.controller;

import com.webapp.resetPassword.entity.User;
import com.webapp.resetPassword.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ResetPasswordController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepo;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/reset")
    public String showResetForm(Model model) {
        model.addAttribute("user", new User());

        return "reset";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(Model model, User user) {
        User existedUser = userRepo.findByEmail(user.getEmail());

        if(existedUser != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            existedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(existedUser);

            model.addAttribute("user", existedUser);
            return "success";
        } else {
            return "error";
        }
    }
}
