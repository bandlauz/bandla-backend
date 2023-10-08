package uz.nazarovctrl.bandla.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.nazarovctrl.bandla.annotations.validation.Code;
import uz.nazarovctrl.bandla.enums.ValidMessageTypes;
import uz.nazarovctrl.bandla.util.ValidatorUtil;

import java.util.ArrayList;
import java.util.Map;

public class CodeValidator implements ConstraintValidator<Code, String> {

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        Map<ValidMessageTypes, String> messages = ValidatorUtil.getCodeMessages();

        if (code.length() == 4) {
            messages.remove(ValidMessageTypes.LENGTH);
        }

        try {
            Integer.parseInt(code);
            messages.remove(ValidMessageTypes.DIGIT);
        } catch (RuntimeException e) {
        }

        if (messages.size() == 0) {
            return true;
        }

        ValidatorUtil.updateMessage(context, messages.values());
        return false;
    }
}