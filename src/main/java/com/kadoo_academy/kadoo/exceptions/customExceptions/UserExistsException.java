package com.kadoo_academy.kadoo.exceptions.customExceptions;

public class UserExistsException extends RuntimeException{
    public UserExistsException(){
        super();
    }

    public UserExistsException(String mensagem){
        super(mensagem);
    }
}
