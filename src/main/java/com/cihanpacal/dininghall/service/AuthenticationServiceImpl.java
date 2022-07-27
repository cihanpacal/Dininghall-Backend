package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.User;
import com.cihanpacal.dininghall.model.request.AuthenticationRequest;
import com.cihanpacal.dininghall.model.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService{

    private final AuthenticationManager authenticationManager;
    private final AuthenticationTokenService authenticationTokenService;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()
        );


        Authentication authentication=authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetails userDetails=(UserDetails) authentication.getPrincipal();

        AuthenticationResponse authenticationResponse=authenticationTokenService.createAuthenticationToken(userDetails.getUsername());

        return authenticationResponse;
    }



}
