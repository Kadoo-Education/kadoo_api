package com.kadoo_academy.kadoo.exceptions;

import com.kadoo_academy.kadoo.exceptions.customExceptions.*;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleInvalidPassword(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Senha inválida"));
    }

    @ExceptionHandler(EdictExistsException.class)
    private ResponseEntity<String> edictExistsHandler(EdictExistsException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("edict doesn't exist");
    }

    @ExceptionHandler(EdictNotFoundException.class)
    private ResponseEntity<String> edictNotFoundHandler(EdictNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Edict not found");
    }

    @ExceptionHandler(UserExistsException.class)
    private ResponseEntity<String> userExistsHandler(UserExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists.");
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<?> userNotFoundHandler(UserNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Usuário não encontrado"));
    }

    @ExceptionHandler(UserEdictExistException.class)
    private ResponseEntity<String> UserEdictExistSubscribeHandler(UserEdictExistException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe uma inscrição para este usuário!");
    }

    @ExceptionHandler(ProfileNotAuthorizedException.class)
    private ResponseEntity<String> profileNotAuthorized(ProfileNotAuthorizedException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }

}
