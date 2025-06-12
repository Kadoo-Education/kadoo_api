package com.kadoo_academy.kadoo.exceptions;

public class UserEdictExistException extends RuntimeException {
    public UserEdictExistException() {
        super();
    }
    public UserEdictExistException(String mensagem){
        super(mensagem);
    }
}
