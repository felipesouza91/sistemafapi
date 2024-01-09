package com.sistemaf.domain.validations;

import com.sistemaf.infrastructure.validations.AllowedFileExtensionsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AllowedFileExtensionsValidator.class)
@Documented
public @interface AllowedFileExtensions {
    String message() default "{AllowedFileExtensionsValidator.message}";

    String[] value() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
