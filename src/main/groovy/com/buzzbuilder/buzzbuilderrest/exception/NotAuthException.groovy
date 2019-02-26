package com.buzzbuilder.buzzbuilderrest.exception;


public class NotAuthException extends RuntimeException {

    public NotAuthException() {
        this("No certification!");
    }

    public NotAuthException(String message) {
        super(message);
    }
}
