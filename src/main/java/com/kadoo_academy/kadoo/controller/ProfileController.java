package com.kadoo_academy.kadoo.controller;

import com.kadoo_academy.kadoo.dto.response.ProfileUserResponseDTO;
import com.kadoo_academy.kadoo.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me")
public class ProfileController {

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            ProfileUserResponseDTO user = tokenService.decodeToken(token);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
