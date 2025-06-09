package com.kadoo_academy.kadoo.controller;


import com.kadoo_academy.kadoo.dto.*;
import com.kadoo_academy.kadoo.models.User;
import com.kadoo_academy.kadoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<CreateUserDTO> createUser (@RequestBody CreateUserDTO userDTO){
        userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteUserDTO> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserId(@PathVariable("id") Long id){
        Optional<User> user = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/{id}")
        public ResponseEntity<Void> updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserDTO updateUserDTO){
        userService.updateUser(id, updateUserDTO);
        return ResponseEntity.status((HttpStatus.NO_CONTENT)).build();
    }

    @GetMapping()
    public ResponseEntity<Stream<ListUsersDTO>> listUsers(){
        Stream<ListUsersDTO> usersList = userService.listUsers();
        return ResponseEntity.ok().body(usersList);
    }
}