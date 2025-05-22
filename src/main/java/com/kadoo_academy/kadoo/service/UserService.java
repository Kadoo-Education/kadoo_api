package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

//    @Autowired
//    private UserRepository userRepository;

    public String helloWorld(){
        return "Hello World " ;
    }
}
