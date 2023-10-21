package uz.bandla.exp.auth;

import uz.bandla.exp.ResponseException;

import org.springframework.http.HttpStatus;

public class PasswordAlreadySavedException extends ResponseException {
    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private int errorCode = 100;

    public PasswordAlreadySavedException() {
        super("Password is already saved. Password cannot be changed");
    }

    @Override
    public int getCode() {
        return errorCode;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}