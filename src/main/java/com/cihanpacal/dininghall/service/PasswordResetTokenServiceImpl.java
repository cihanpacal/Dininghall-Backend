package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.PasswordResetToken;
import com.cihanpacal.dininghall.model.entity.User;
import com.cihanpacal.dininghall.model.entity.VerificationToken;
import com.cihanpacal.dininghall.model.request.PasswordResetTokenRequest;
import com.cihanpacal.dininghall.repository.PasswordResetTokenRepository;
import com.cihanpacal.dininghall.validation.PasswordResetTokenValidator;
import com.cihanpacal.dininghall.validation.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService{

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordResetTokenValidator passwordResetTokenValidator;
    private final UserValidator userValidator;



    @Override
    public String createPasswordResetToken(@Valid PasswordResetTokenRequest passwordResetTokenRequest) {
        User user=userValidator.checkUserExistByEmail(passwordResetTokenRequest.getUserEmail());
        userValidator.checkUserNotConfirmed(user);

        PasswordResetToken passwordResetToken=new PasswordResetToken();

        passwordResetToken.setUsedToken(passwordResetTokenRequest.isUsedToken());
        passwordResetToken.setExpiryDate(passwordResetTokenRequest.getExpiryDate());
        passwordResetToken.setUser(user);
        passwordResetToken.setToken(UUID.randomUUID().toString());

        passwordResetTokenRepository.save(passwordResetToken);


        return passwordResetToken.getToken();
    }

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        PasswordResetToken passwordResetToken=passwordResetTokenValidator.checkPasswordResetTokenExist(token);
        passwordResetTokenValidator.checkPasswordResetTokenUsed(passwordResetToken);
        passwordResetTokenValidator.checkPasswordResetTokenExpiry(passwordResetToken);
        return passwordResetToken;
    }

    @Override
    public void updatePasswordResetToken(PasswordResetToken passwordResetToken) {
        passwordResetTokenRepository.save(passwordResetToken);
    }


}
