package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.PasswordResetToken;
import com.cihanpacal.dininghall.model.entity.VerificationToken;

public interface PasswordResetTokenValidator {
    PasswordResetToken checkPasswordResetTokenExist(String token);
    void checkPasswordResetTokenUsed(PasswordResetToken passwordResetToken);
    void checkPasswordResetTokenExpiry(PasswordResetToken passwordResetToken);
}
