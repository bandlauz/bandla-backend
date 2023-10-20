package uz.bandla.annotations.validation;

import uz.bandla.validator.CodeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CodeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Code {
    String message() default "Invalid code";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
