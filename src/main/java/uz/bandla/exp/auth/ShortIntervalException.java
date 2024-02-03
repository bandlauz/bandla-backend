package uz.bandla.exp.auth;

import uz.bandla.exp.ResponseException;

import org.springframework.http.HttpStatus;

public class ShortIntervalException extends ResponseException {
    private int code = 100;
    private HttpStatus status = HttpStatus.TOO_MANY_REQUESTS;
    private long waitTime;

    public ShortIntervalException(String message) {
        super(message);
    }

    public ShortIntervalException(String message, long waitTime) {
        super(message);
        this.waitTime = waitTime;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    public long getWaitTime() {
        return waitTime;
    }
}
