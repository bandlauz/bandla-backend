package ctrl.nazarov.web.exp;

public class InvalidSmsCode extends RuntimeException {
    public InvalidSmsCode(String message) {
        super(message);
    }
}
