package com.kadoo_academy.kadoo.exceptions.customExceptions;

public class ProfileNotAuthorizedException extends RuntimeException {
    public ProfileNotAuthorizedException(String message) {
        super(message);
    }
}
