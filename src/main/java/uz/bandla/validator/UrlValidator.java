package uz.bandla.validator;

import uz.bandla.annotations.validation.Url;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlValidator implements ConstraintValidator<Url, String> {
    private boolean canBeNull;

    @Override
    public void initialize(Url constraintAnnotation) {
        this.canBeNull = constraintAnnotation.canBeNull();
    }

    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {
        if (canBeNull && url == null) {
            return true;
        }

        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}