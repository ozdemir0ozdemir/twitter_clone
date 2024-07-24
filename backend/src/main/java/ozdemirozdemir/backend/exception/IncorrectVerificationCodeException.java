package ozdemirozdemir.backend.exception;

public class IncorrectVerificationCodeException extends RuntimeException {

    public IncorrectVerificationCodeException(String username, Long code) {
        super(String.format("The verification code : %d is not valid nor correct!", code));
    }
}
