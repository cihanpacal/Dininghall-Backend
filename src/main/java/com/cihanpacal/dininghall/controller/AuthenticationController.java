package com.cihanpacal.dininghall.controller;


import com.cihanpacal.dininghall.model.request.AuthenticationRequest;
import com.cihanpacal.dininghall.model.response.AuthenticationResponse;
import com.cihanpacal.dininghall.service.AuthenticationService;
import com.cihanpacal.dininghall.service.AuthenticationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/1.0/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationTokenService authenticationTokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest authenticationRequest
            ){
        AuthenticationResponse authenticationResponse=authenticationService.authenticate(authenticationRequest);

        return ResponseEntity.ok(authenticationResponse);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(value = "Authorization",required = true) String token){
        authenticationTokenService.deleteToken(token);
        return ResponseEntity.ok().build();
    }
}
