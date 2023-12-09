package uz.bandla.exp;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ResponseException {

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
