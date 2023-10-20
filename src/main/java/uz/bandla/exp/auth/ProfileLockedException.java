package uz.bandla.exp.auth;

import uz.bandla.exp.ResponseException;

import org.springframework.http.HttpStatus;

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
