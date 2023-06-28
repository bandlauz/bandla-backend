package uz.nazarovctrl.bandla.exp;

public class PhoneNumberIsNotRegistered extends RuntimeException {
    public PhoneNumberIsNotRegistered(String message) {
        super(message);
    }
}
