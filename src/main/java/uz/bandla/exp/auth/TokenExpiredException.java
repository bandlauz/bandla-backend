package uz.bandla.exp.auth;

import uz.bandla.exp.ResponseException;

import org.springframework.http.HttpStatus;

public class TokenExpiredException extends ResponseException {
    private int code = 100;
    private HttpStatus status = HttpStatus.FORBIDDEN;

    public TokenExpiredException(String message) {
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
