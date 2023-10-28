package uz.bandla.exp.auth;

import org.springframework.http.HttpStatus;
import uz.bandla.exp.ResponseException;

public class TelegramLoginException extends ResponseException {

    public TelegramLoginException() {
        super("Telegram orqali kirish to'liq yakunlanmagan");
    }

    public TelegramLoginException(String message) {
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
