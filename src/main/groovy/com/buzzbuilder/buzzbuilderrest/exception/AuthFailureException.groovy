package com.buzzbuilder.buzzbuilderrest.exception;


public class AuthFailureException extends RuntimeException {

    public AuthFailureException() {
        this("Authentication failed!");
    }

    public AuthFailureException(String message) {
        super(message);
    }
}
