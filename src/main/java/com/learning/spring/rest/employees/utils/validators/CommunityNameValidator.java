package com.learning.spring.rest.employees.utils.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CommunityNameValidator implements ConstraintValidator<CommunityNameConstraint, String> {
    @Override
    public void initialize(CommunityNameConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String nameField, ConstraintValidatorContext context) {
        return nameField != null && nameField.matches("[A-Z]*") && (nameField.length() > 1) && (nameField.length() < 16);
    }
}
