package com.buzzbuilder.buzzbuilderrest.exception;


public class ArgumentsFailureException extends RuntimeException {

    public ArgumentsFailureException() {
        this("Parameter error");
    }

    public ArgumentsFailureException(String message) {
        super(message);
    }
}
