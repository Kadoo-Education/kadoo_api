package com.kadoo_academy.kadoo.controller;


import com.kadoo_academy.kadoo.models.User;
import com.kadoo_academy.kadoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<String> createUser (@RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping()
    public List<User> listUsers(){
        return userService.listUsers();
    }
}