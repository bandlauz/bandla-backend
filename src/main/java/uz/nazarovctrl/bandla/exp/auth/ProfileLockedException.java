package uz.nazarovctrl.bandla.exp.auth;

import org.springframework.http.HttpStatus;
import uz.nazarovctrl.bandla.exp.ResponseException;

public class ProfileLockedException extends ResponseException {
    private final int code = 100;
    private HttpStatus status = HttpStatus.FORBIDDEN;

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
