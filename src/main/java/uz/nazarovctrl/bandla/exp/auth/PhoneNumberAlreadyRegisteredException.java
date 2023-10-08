package uz.nazarovctrl.bandla.exp.auth;

import org.springframework.http.HttpStatus;
import uz.nazarovctrl.bandla.exp.ResponseException;

public class PhoneNumberAlreadyRegisteredException extends ResponseException {
    public PhoneNumberAlreadyRegisteredException(String message) {
        super(message);
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public HttpStatus getStatus() {
        return null;
    }
}
