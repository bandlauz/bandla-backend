package uz.bandla.exp;

import org.springframework.http.HttpStatus;

public abstract class ResponseException extends RuntimeException {
    public ResponseException(String message) {
        super(message);
    }

    public abstract int getCode();

    public abstract HttpStatus getStatus();
}
