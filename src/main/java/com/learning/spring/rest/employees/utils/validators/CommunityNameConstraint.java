package com.learning.spring.rest.employees.utils.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CommunityNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CommunityNameConstraint {
    String message() default "Invalid community name. Must match [A-Z]* pattern and size should be between 2 and 16 characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
