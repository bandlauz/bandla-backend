package uz.nazarovctrl.bandla.exp.auth;

import org.springframework.http.HttpStatus;
import uz.nazarovctrl.bandla.exp.ResponseException;

public class ShortIntervalException extends ResponseException {
    private int code = 100;
    private HttpStatus status = HttpStatus.TOO_MANY_REQUESTS;

    public ShortIntervalException(String message) {
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
