package ctrl.nazarov.web.exp;

public class PhoneNumberIsNotRegistered extends RuntimeException {
    public PhoneNumberIsNotRegistered(String message) {
        super(message);
    }
}
