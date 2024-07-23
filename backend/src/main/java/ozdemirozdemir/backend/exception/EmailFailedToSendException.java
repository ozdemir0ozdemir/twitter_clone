package ozdemirozdemir.backend.exception;

public class EmailFailedToSendException extends RuntimeException {
    public EmailFailedToSendException(Exception e) {
        super(e.getMessage());
    }
}
