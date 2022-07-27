package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.AuthenticationToken;
import com.cihanpacal.dininghall.model.entity.VerificationToken;

public interface AuthenticationTokenValidator {
    AuthenticationToken checkAuthenticationTokenExist(String token);
}
