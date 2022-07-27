package com.cihanpacal.dininghall.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {

    String message() default "{constraint.ValidEmail}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
