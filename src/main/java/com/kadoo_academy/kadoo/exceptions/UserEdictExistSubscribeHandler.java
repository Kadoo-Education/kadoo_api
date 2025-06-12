package com.kadoo_academy.kadoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserEdictExistSubscribeHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserEdictExistException.class)
    private ResponseEntity<String> UserEdictExistSubscribeHandler(UserEdictExistException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe uma inscrição para este usuário!");
    }
}
