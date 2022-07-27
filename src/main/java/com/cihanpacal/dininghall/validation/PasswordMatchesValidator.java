package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.request.UpdatePasswordRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator  implements ConstraintValidator<PasswordMatches,Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UpdatePasswordRequest updatePasswordRequest=(UpdatePasswordRequest) o;
        return updatePasswordRequest.getNewPassword().equals(updatePasswordRequest.getNewPasswordRepeat());
    }
}
