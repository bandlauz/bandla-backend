package uz.nazarovctrl.library.exp;

public class InvalidSmsCode extends RuntimeException {
    public InvalidSmsCode(String message) {
        super(message);
    }
}
