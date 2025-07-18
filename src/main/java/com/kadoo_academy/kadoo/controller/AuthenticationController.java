package com.kadoo_academy.kadoo.controller;

import com.kadoo_academy.kadoo.dto.request.LoginRequest;
import com.kadoo_academy.kadoo.dto.response.TokenResponseDTO;
import com.kadoo_academy.kadoo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequest login) {
        try {
            TokenResponseDTO token = authenticationService.login(login);

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
