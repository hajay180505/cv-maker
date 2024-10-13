package com.cvmaker.cvmaker.exception;

public class NoUserFoundException extends Exception{
    String message;
    public NoUserFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}


