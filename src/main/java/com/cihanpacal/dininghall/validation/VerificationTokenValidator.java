package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.VerificationToken;

public interface VerificationTokenValidator {
    VerificationToken checkVerificationTokenExist(String token);
    void checkVerificationTokenUsed(VerificationToken verificationToken);
    void checkVerificationTokenExpiry(VerificationToken verificationToken);
}
