package uz.bandla.exp.auth;

import uz.bandla.exp.ResponseException;

import org.springframework.http.HttpStatus;

public class PhoneNumberAlreadyRegisteredException extends ResponseException {
    private int code = 100;
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public PhoneNumberAlreadyRegisteredException(String message) {
        super(message);
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
