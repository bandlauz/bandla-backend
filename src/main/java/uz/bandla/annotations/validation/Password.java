package uz.bandla.annotations.validation;

import uz.bandla.validator.PasswordValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "Password does not meet the requirement";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
