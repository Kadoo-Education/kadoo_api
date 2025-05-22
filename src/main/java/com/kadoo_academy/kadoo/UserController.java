package com.kadoo_academy.kadoo;


import com.kadoo_academy.kadoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String helloWorld(){
        return userService.helloWorld();
    }
}
