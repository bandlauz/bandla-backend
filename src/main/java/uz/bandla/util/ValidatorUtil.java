package uz.bandla.util;

import uz.bandla.enums.ValidMessageTypes;

import jakarta.validation.ConstraintValidatorContext;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ValidatorUtil {

    public static Map<ValidMessageTypes, String> getPasswordMessages() {
        Map<ValidMessageTypes, String> messages = new HashMap<>();
        messages.put(ValidMessageTypes.LENGTH, "password must contain at least 8 characters");
        messages.put(ValidMessageTypes.LOWERCASE, "password must contain at least 1 lowercase letter");
        messages.put(ValidMessageTypes.UPPERCASE, "password must contain at least 1 uppercase letter");
        messages.put(ValidMessageTypes.DIGIT, "password must contain at least 1 digit");
        return messages;
    }

    public static Map<ValidMessageTypes, String> getCodeMessages() {
        Map<ValidMessageTypes, String> messages = new HashMap<>();
        messages.put(ValidMessageTypes.LENGTH, "code must contain 4 characters");
        messages.put(ValidMessageTypes.DIGIT, "code must consist of digits only");
        return messages;
    }

    public static ConstraintValidatorContext updateMessage(ConstraintValidatorContext context, Collection<String> values) {
        String messageTemplate = String.join(",", values);

        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return context;
    }
}
