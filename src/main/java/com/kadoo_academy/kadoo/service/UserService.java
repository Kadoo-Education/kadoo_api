package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.exceptions.UserExistsException;
import com.kadoo_academy.kadoo.models.User;
import com.kadoo_academy.kadoo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listUsers(){
        return userRepository.findAll();
    }

    public void createUser(User user){
        Optional<User> userExists= Optional.ofNullable(userRepository.findByEmail(user.getEmail()));

        if(userExists.isPresent()){
            throw new UserExistsException(user.getEmail());
        }
        userRepository.save(user);
    }

}