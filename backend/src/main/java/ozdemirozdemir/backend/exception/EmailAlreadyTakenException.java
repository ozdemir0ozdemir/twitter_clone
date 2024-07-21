package ozdemirozdemir.backend.exception;


public class EmailAlreadyTakenException extends RuntimeException {


    public EmailAlreadyTakenException(String email) {
        super(String.format("Email: %s is already taken.", email));
    }
}
