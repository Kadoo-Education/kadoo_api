package com.kadoo_academy.kadoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserExistsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserExistsException.class)
    private ResponseEntity<String> userExistsHandler(UserExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists.");
    }
}
