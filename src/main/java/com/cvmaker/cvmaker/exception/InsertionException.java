package com.cvmaker.cvmaker.exception;

/**
 * The type Insertion exception.
 */
public class InsertionException extends Exception {
    /**
     * The Message.
     */
    String message;

    /**
     * Instantiates a new Insertion exception.
     *
     * @param message the message
     */
    public InsertionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
