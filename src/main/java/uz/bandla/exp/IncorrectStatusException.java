package uz.bandla.exp;

import org.springframework.http.HttpStatus;

public class IncorrectStatusException extends ResponseException {

    public IncorrectStatusException(String message) {
        super(message);
    }

    @Override
    public int getCode() {
        return 100;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
