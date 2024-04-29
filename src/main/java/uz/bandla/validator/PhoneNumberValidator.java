package uz.bandla.validator;

import uz.bandla.annotations.validation.PhoneNumber;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    private final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    @Override
    public boolean isValid(String phoneNumberField, ConstraintValidatorContext context) {
        if (phoneNumberField == null || phoneNumberField.isBlank()) {
            return false;
        }
        final String REGEX = "\\d+";
        if (!phoneNumberField.matches(REGEX)) {
            return false;
        }
        phoneNumberField = "+" + phoneNumberField;
        Phonenumber.PhoneNumber phoneNumber;
        try {
            phoneNumber = PhoneNumberUtil.getInstance()
                    .parse(phoneNumberField, Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name());
        } catch (NumberParseException e) {
            return false;
        }
        return phoneNumberUtil.isValidNumberForRegion(phoneNumber, "UZ");
    }
}