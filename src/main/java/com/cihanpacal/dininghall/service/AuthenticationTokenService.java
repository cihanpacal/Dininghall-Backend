package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.AuthenticationToken;
import com.cihanpacal.dininghall.model.response.AuthenticationResponse;

public interface AuthenticationTokenService {
    AuthenticationResponse createAuthenticationToken(String email);
    void deleteTokensByUserId(Long userId);

    void deleteToken(String token);
}
