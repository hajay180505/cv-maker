package cvMaker.exception;

public class InsertionException extends Exception {
    String message;
    public InsertionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
