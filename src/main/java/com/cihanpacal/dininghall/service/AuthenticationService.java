package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.User;
import com.cihanpacal.dininghall.model.request.AuthenticationRequest;
import com.cihanpacal.dininghall.model.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
