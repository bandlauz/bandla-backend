package uz.bandla.validator;

import uz.bandla.annotations.validation.NullOrNotBlank;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {

    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || !value.isBlank();
    }
}