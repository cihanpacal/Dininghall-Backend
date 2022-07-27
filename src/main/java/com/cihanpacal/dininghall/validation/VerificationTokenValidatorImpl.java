package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.ExpiryTokenException;
import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.error.UsedVerificationTokenException;
import com.cihanpacal.dininghall.model.entity.VerificationToken;
import com.cihanpacal.dininghall.model.entity.Warehouse;
import com.cihanpacal.dininghall.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VerificationTokenValidatorImpl implements VerificationTokenValidator{

    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken checkVerificationTokenExist(String token) {
        Optional<VerificationToken> optional=verificationTokenRepository.findByToken(token);

        VerificationToken verificationToken=optional.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.VerificationTokenNotFound",new Object[]{});
        });

        return  verificationToken;
    }

    @Override
    public void checkVerificationTokenUsed(VerificationToken verificationToken) {
        if(verificationToken.isUsedToken()){
            throw new UsedVerificationTokenException("exception.UsedVerificationTokenException");
        }
    }

    @Override
    public void checkVerificationTokenExpiry(VerificationToken verificationToken) {
        Boolean isBefore=verificationToken.getExpiryDate().isBefore(LocalDateTime.now());
        if(isBefore){
            throw new ExpiryTokenException("exception.ExpiryVerificationTokenException");
        }
    }
}
