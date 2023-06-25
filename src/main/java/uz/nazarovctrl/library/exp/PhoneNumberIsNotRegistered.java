package uz.nazarovctrl.library.exp;

public class PhoneNumberIsNotRegistered extends RuntimeException {
    public PhoneNumberIsNotRegistered(String message) {
        super(message);
    }
}
