package com.cvmaker.cvmaker.exception;

/**
 * The type No user found exception.
 */
public class NoUserFoundException extends Exception{
    /**
     * The Message.
     */
    String message;

    /**
     * Instantiates a new No user found exception.
     *
     * @param message the message
     */
    public NoUserFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}


