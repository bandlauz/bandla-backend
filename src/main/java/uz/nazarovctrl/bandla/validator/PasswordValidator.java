package uz.nazarovctrl.bandla.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.nazarovctrl.bandla.annotations.validation.Password;
import uz.nazarovctrl.bandla.enums.ValidMessageTypes;
import uz.nazarovctrl.bandla.util.ValidatorUtil;

import java.util.ArrayList;
import java.util.Map;


public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        Map<ValidMessageTypes, String> messages = ValidatorUtil.getPasswordMessages();
        if (code.length() >= 8) {
            messages.remove(ValidMessageTypes.LENGTH);
        }

        for (char c : code.toCharArray()) {
            if (Character.isLowerCase(c)) {
                messages.remove(ValidMessageTypes.LOWERCASE);
            } else if (Character.isUpperCase(c)) {
                messages.remove(ValidMessageTypes.UPPERCASE);
            } else if (Character.isDigit(c)) {
                messages.remove(ValidMessageTypes.DIGIT);
            }
            if (messages.size() == 0) {
                return true;
            }
        }

        ValidatorUtil.updateMessage(context, messages.values());
        return false;
    }
}
