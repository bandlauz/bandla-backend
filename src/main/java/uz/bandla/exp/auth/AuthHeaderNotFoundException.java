package uz.bandla.exp.auth;

import uz.bandla.exp.ResponseException;

import org.springframework.http.HttpStatus;

public class AuthHeaderNotFoundException extends ResponseException {
    public AuthHeaderNotFoundException() {
        super("Authorization header not found");
    }

    @Override
    public int getCode() {
        return 101;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}