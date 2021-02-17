package com.danzan.springjwt.Childs.controllers;

import com.danzan.springjwt.Childs.Service.UserService;
import com.danzan.springjwt.Childs.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/all")
    List<User> getAllUser() {
        return userService.findAllUser();
    }




}
