package cvMaker.exception;

public class InvalidFieldValueException extends Exception {
    String message;

    public InvalidFieldValueException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
