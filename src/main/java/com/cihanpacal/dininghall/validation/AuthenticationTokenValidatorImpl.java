package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.model.entity.AuthenticationToken;
import com.cihanpacal.dininghall.model.entity.VerificationToken;
import com.cihanpacal.dininghall.repository.AuthenticationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationTokenValidatorImpl implements AuthenticationTokenValidator{

    private final AuthenticationTokenRepository authenticationTokenRepository;

    @Override
    public AuthenticationToken checkAuthenticationTokenExist(String token) {
        Optional<AuthenticationToken> optionalAuthenticationToken=authenticationTokenRepository
                .findByToken(token);

        AuthenticationToken authenticationToken=optionalAuthenticationToken.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.AuthenticationTokenNotFound",new Object[]{});
        });

        return  authenticationToken;
    }
}
