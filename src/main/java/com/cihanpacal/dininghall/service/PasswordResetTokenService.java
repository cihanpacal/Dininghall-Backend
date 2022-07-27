package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.PasswordResetToken;
import com.cihanpacal.dininghall.model.entity.User;
import com.cihanpacal.dininghall.model.entity.VerificationToken;
import com.cihanpacal.dininghall.model.request.PasswordResetTokenRequest;

public interface PasswordResetTokenService {
    String createPasswordResetToken(PasswordResetTokenRequest passwordResetTokenRequest);
    PasswordResetToken getPasswordResetToken(String token);
    void updatePasswordResetToken(PasswordResetToken passwordResetToken);
}
