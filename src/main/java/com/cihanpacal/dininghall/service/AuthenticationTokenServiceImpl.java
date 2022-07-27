package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.AuthenticationToken;
import com.cihanpacal.dininghall.model.entity.User;
import com.cihanpacal.dininghall.model.response.AuthenticationResponse;
import com.cihanpacal.dininghall.repository.AuthenticationTokenRepository;
import com.cihanpacal.dininghall.validation.AuthenticationTokenValidator;
import com.cihanpacal.dininghall.validation.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationTokenServiceImpl implements AuthenticationTokenService{

    private final UserValidator userValidator;
    private final AuthenticationTokenRepository authenticationTokenRepository;
    private final AuthenticationTokenValidator authenticationTokenValidator;

    @Override
    public AuthenticationResponse createAuthenticationToken(String email) {

        User user=userValidator.checkUserExistByEmail(email);
        String token= UUID.randomUUID().toString();
        AuthenticationToken authenticationToken=new AuthenticationToken();
        authenticationToken.setToken(token);
        authenticationToken.setExpiryDate(LocalDateTime.now().plusHours(24));
        authenticationToken.setUser(user);

        authenticationTokenRepository.save(authenticationToken);

        AuthenticationResponse authenticationResponse=new AuthenticationResponse();
        authenticationResponse.setId(user.getId());
        authenticationResponse.setFirstName(user.getFirstName());
        authenticationResponse.setLastName(user.getLastName());
        authenticationResponse.setEmail(user.getEmail());
        authenticationResponse.setRole(user.getRole().toString().substring(5));
        authenticationResponse.setToken(token);
        authenticationResponse.setTokenExpiryTime(authenticationToken.getExpiryDate());

        return authenticationResponse;
    }

    @Override
    public void deleteTokensByUserId(Long userId) {
        authenticationTokenRepository.deleteByUser_Id(userId);
    }

    @Override
    public void deleteToken(String token) {
        String extractedToken=token.substring(7);
        AuthenticationToken authenticationToken=authenticationTokenValidator
                .checkAuthenticationTokenExist(extractedToken);
        authenticationTokenRepository.delete(authenticationToken);
    }

}
