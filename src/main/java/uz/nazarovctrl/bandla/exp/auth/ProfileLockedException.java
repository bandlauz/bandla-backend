package uz.nazarovctrl.bandla.exp.auth;

import org.springframework.http.HttpStatus;
import uz.nazarovctrl.bandla.exp.ResponseException;

public class ProfileLockedException extends ResponseException {
    private int code = 100;
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public ProfileLockedException() {
        super("Profile is locked");
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
