package com.kadoo_academy.kadoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EdictNotFoundHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EdictNotFoundException.class)
    private ResponseEntity<String> edictNotFoundHandler(EdictNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Edict not found");
    }
}
