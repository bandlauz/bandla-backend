package uz.bandla.exp.auth;

import uz.bandla.exp.ResponseException;

import org.springframework.http.HttpStatus;

public class ProfileStatusIncorrectException extends ResponseException {
    private int code = 100;
    private HttpStatus status = HttpStatus.FORBIDDEN;

    public ProfileStatusIncorrectException() {
        super("For setting password profile status must be NOT_VERIFIED");
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
