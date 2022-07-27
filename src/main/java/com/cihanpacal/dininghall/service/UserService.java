package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.request.ChangeUserLockStatusRequest;
import com.cihanpacal.dininghall.model.request.UserRequest;
import com.cihanpacal.dininghall.model.request.EmailRequest;
import com.cihanpacal.dininghall.model.request.UpdatePasswordRequest;
import com.cihanpacal.dininghall.model.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.Locale;
import java.util.Optional;

public interface UserService {
    Long createUser(UserRequest createUserRequest, Locale locale);
    void confirmAccount(String token,Locale locale);
    void resendVerificationToken(EmailRequest emailRequest, Locale locale);
    void createPasswordResetToken(EmailRequest emailRequest,Locale locale);
    void sendNewPassword(String token, Locale locale);
    void changeLockStatus(Long id,ChangeUserLockStatusRequest changeUserLockStatusRequest, Authentication authentication);

    void updatePassword(String email, UpdatePasswordRequest updatePasswordRequest);

    void updateEmail(Authentication authentication, EmailRequest emailRequest,Locale locale);

    UserResponse getUser(Long id);

    Page<UserResponse> getUsers(Optional<String> filter, Pageable pageable);

    void updateUser(Long id,UserRequest userRequest,Locale locale);
}
