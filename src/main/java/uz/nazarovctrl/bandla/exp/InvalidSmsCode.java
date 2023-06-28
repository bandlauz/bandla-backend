package uz.nazarovctrl.bandla.exp;

public class InvalidSmsCode extends RuntimeException {
    public InvalidSmsCode(String message) {
        super(message);
    }
}
