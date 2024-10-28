package com.cvmaker.cvmaker.exception;

/**
 * The type Invalid field value exception.
 */
public class InvalidFieldValueException extends Exception {
    /**
     * The Message.
     */
    String message;

    /**
     * Instantiates a new Invalid field value exception.
     *
     * @param message the message
     */
    public InvalidFieldValueException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
