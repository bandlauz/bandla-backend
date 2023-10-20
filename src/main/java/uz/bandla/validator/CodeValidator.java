package uz.bandla.validator;

import uz.bandla.annotations.validation.Code;
import uz.bandla.enums.ValidMessageTypes;
import uz.bandla.util.ValidatorUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

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
        } catch (RuntimeException ignored) {
        }

        if (messages.size() == 0) {
            return true;
        }

        ValidatorUtil.updateMessage(context, messages.values());
        return false;
    }
}