package com.buzzbuilder.buzzbuilderrest.exception;


public class NotAuthorityException extends RuntimeException{

    public NotAuthorityException() {
        this("Permission denied!");
    }

    public NotAuthorityException(String message) {
        super(message);
    }
}
