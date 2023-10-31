package com.sistemaf.domain.validations;

import com.sistemaf.infrastructure.validations.AllowedContentTypesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AllowedContentTypesValidator.class)
@Documented
public @interface AllowedContentTypes {
    String message() default "{AllowedContentTypesValidator.message}";

    String[] value() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
