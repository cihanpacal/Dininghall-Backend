package com.cihanpacal.dininghall.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Documented
public @interface ValidPassword {
    String message() default "{constraint.ValidPassword}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
