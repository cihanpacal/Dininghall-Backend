package com.cihanpacal.dininghall.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {

    String message() default "{constraint.PasswordMatches}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
