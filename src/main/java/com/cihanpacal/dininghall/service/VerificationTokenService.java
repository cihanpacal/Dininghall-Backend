package com.cihanpacal.dininghall.service;
import com.cihanpacal.dininghall.model.entity.VerificationToken;
import com.cihanpacal.dininghall.model.request.VerificationTokenRequest;

import java.util.Optional;

public interface VerificationTokenService {
    String createVerificationToken(VerificationTokenRequest verificationTokenRequest);
    VerificationToken getVerificationToken(String token);
    void updateVerificationToken(VerificationToken verificationToken);
    void deleteVerificationTokensByUserId(Long userId);

}
