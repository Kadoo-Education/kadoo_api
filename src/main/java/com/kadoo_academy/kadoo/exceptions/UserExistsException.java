package com.kadoo_academy.kadoo.exceptions;

public class UserExistsException extends RuntimeException{
    public UserExistsException(){
        super("User already exists!");
    }

    public UserExistsException(String mensagem){
        super(mensagem);
    }
}
