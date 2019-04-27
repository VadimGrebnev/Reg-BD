package com.first.controller;

import com.first.domain.Role;
import com.first.domain.User;
import com.first.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user, Map<String,Object>model) {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null){
            model.put("message","User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.User));
        userRepo.save(user);
        return "redirect:/login";


    }
}