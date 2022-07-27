package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.User;
import com.cihanpacal.dininghall.model.entity.VerificationToken;
import com.cihanpacal.dininghall.model.request.VerificationTokenRequest;
import com.cihanpacal.dininghall.repository.VerificationTokenRepository;
import com.cihanpacal.dininghall.validation.UserValidator;
import com.cihanpacal.dininghall.validation.VerificationTokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class VerificationTokenServiceImpl implements VerificationTokenService{

    private final VerificationTokenRepository verificationTokenRepository;
    private final VerificationTokenValidator verificationTokenValidator;
    private final UserValidator userValidator;



    @Override
    public String createVerificationToken(@Valid VerificationTokenRequest verificationTokenRequest) {
        User user=userValidator.checkUserExistByEmail(verificationTokenRequest.getUserEmail());
        userValidator.checkUserAlreadyConfirmed(user);

        VerificationToken verificationToken=new VerificationToken();

        verificationToken.setUsedToken(verificationTokenRequest.isUsedToken());
        verificationToken.setExpiryDate(verificationTokenRequest.getExpiryDate());
        verificationToken.setUser(user);
        verificationToken.setToken(UUID.randomUUID().toString());

        verificationTokenRepository.save(verificationToken);


        return verificationToken.getToken();
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        VerificationToken verificationToken=verificationTokenValidator.checkVerificationTokenExist(token);
        verificationTokenValidator.checkVerificationTokenUsed(verificationToken);
        verificationTokenValidator.checkVerificationTokenExpiry(verificationToken);
        return verificationToken;
    }

    @Override
    public void updateVerificationToken(VerificationToken verificationToken) {
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public void deleteVerificationTokensByUserId(Long userId) {
        verificationTokenRepository.deleteByUser_Id(userId);
    }


}
