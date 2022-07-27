package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.ExpiryTokenException;
import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.error.UsedVerificationTokenException;
import com.cihanpacal.dininghall.model.entity.PasswordResetToken;
import com.cihanpacal.dininghall.repository.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class PasswordResetTokenValidatorImpl implements PasswordResetTokenValidator{

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public PasswordResetToken checkPasswordResetTokenExist(String token) {
        Optional<PasswordResetToken> optionalPasswordResetToken=passwordResetTokenRepository
                .findByToken(token);

        PasswordResetToken passwordResetToken=optionalPasswordResetToken.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.PasswordResetTokenNotFound",new Object[]{});
        });

        return  passwordResetToken;
    }

    @Override
    public void checkPasswordResetTokenUsed(PasswordResetToken passwordResetToken) {
        if(passwordResetToken.isUsedToken()){
            throw new UsedVerificationTokenException("exception.UsedPasswordResetTokenException");
        }
    }

    @Override
    public void checkPasswordResetTokenExpiry(PasswordResetToken passwordResetToken) {
        Boolean isBefore=passwordResetToken.getExpiryDate().isBefore(LocalDateTime.now());
        if(isBefore){
            throw new ExpiryTokenException("exception.ExpiryPasswordResetTokenException");
        }
    }
}
