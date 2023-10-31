package com.sistemaf.infrastructure.validations;

import com.sistemaf.domain.validations.AllowedFileExtensions;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AllowedFileExtensionsValidator implements ConstraintValidator<AllowedFileExtensions, String> {

    private String[] allowedExtensions = {};

    public final void initialize(final AllowedFileExtensions annotation) {
        allowedExtensions = annotation.value();
    }

    public final boolean isValid(final String fileName,
                                 final ConstraintValidatorContext context) {

        if (fileName == null || fileName.isBlank()){
            return true;
        }

        if (allowedExtensions.length == 0) {
            return true;
        }

        for (String extension : this.allowedExtensions) {
            if (fileName.endsWith("."+extension)) {
                return true;
            }
        }

        var contextImpl = (ConstraintValidatorContextImpl) context;
        contextImpl.addMessageParameter("extensions", String.join(", ", this.allowedExtensions));

        return false;
    }
}
