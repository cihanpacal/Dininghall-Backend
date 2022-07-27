package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.error.CannotChangeEnabledUserEmailException;
import com.cihanpacal.dininghall.error.InvalidOldPasswordException;
import com.cihanpacal.dininghall.event.*;
import com.cihanpacal.dininghall.model.entity.*;
import com.cihanpacal.dininghall.model.request.*;
import com.cihanpacal.dininghall.model.response.UserResponse;
import com.cihanpacal.dininghall.repository.UserRepository;
import com.cihanpacal.dininghall.config.security.UserDetailsImpl;
import com.cihanpacal.dininghall.util.*;
import com.cihanpacal.dininghall.validation.UserValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final VerificationTokenService verificationTokenService;
    private final AuthenticationTokenService authenticationTokenService;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenService passwordResetTokenService;
    private final ModelMapper modelMapper;

    @Override
    public Long createUser(UserRequest createUserRequest, Locale locale) {

        userValidator.checkUserAlreadyExist(createUserRequest.getEmail());
        User user=new User();

        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(passwordEncoder.encode(RandomPasswordGenerator.generate()));
        user.setRole(Role.ROLE_USER);
        user.setEnabled(false);
        user.setNonLocked(true);

        userRepository.save(user);

        VerificationTokenRequest verificationTokenRequest=new VerificationTokenRequest();
        verificationTokenRequest.setUsedToken(false);
        verificationTokenRequest.setUserEmail(user.getEmail());
        verificationTokenRequest.setExpiryDate(LocalDateTime.now().plusHours(24));


        String token=verificationTokenService.createVerificationToken(verificationTokenRequest);



        OnRegistrationCompleteEvent onRegistrationCompleteEvent=new OnRegistrationCompleteEvent(
                user.getEmail(),
                token,
                locale
        );

        applicationEventPublisher.publishEvent(onRegistrationCompleteEvent);


        return user.getId();
    }

    @Override
    public void resendVerificationToken(EmailRequest emailRequest, Locale locale) {

        String email=emailRequest.getEmail();

        VerificationTokenRequest verificationTokenRequest=new VerificationTokenRequest();
        verificationTokenRequest.setExpiryDate(LocalDateTime.now().plusHours(24));
        verificationTokenRequest.setUsedToken(false);
        verificationTokenRequest.setUserEmail(email);


        String token=verificationTokenService.createVerificationToken(verificationTokenRequest);

        OnRegistrationCompleteEvent onRegistrationCompleteEvent=new OnRegistrationCompleteEvent(
                email,
                token,
                locale
        );

        applicationEventPublisher.publishEvent(onRegistrationCompleteEvent);
    }

    @Override
    public void confirmAccount(String token,Locale locale) {
        VerificationToken verificationToken=verificationTokenService.getVerificationToken(token);
        User user=verificationToken.getUser();
        userValidator.checkUserAlreadyConfirmed(user);

        String newPassword=RandomPasswordGenerator.generate();
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        verificationToken.setUsedToken(true);
        verificationTokenService.updateVerificationToken(verificationToken);

        OnConfirmationSuccessEvent onConfirmationSuccessEvent=new OnConfirmationSuccessEvent(
                user.getEmail(),
                newPassword,
                locale
        );

        applicationEventPublisher.publishEvent(onConfirmationSuccessEvent);
    }



    @Override
    public void createPasswordResetToken(EmailRequest emailRequest, Locale locale) {

        String email=emailRequest.getEmail();

        PasswordResetTokenRequest passwordResetTokenRequest=new PasswordResetTokenRequest();
        passwordResetTokenRequest.setUsedToken(false);
        passwordResetTokenRequest.setUserEmail(email);
        passwordResetTokenRequest.setExpiryDate(LocalDateTime.now().plusHours(24));

        String token=passwordResetTokenService.createPasswordResetToken(passwordResetTokenRequest);



        OnPasswordResetEvent onPasswordResetEvent=new OnPasswordResetEvent(
                email,
                token,
                locale
        );

        applicationEventPublisher.publishEvent(onPasswordResetEvent);
    }

    @Override
    public void sendNewPassword(String token, Locale locale) {
        PasswordResetToken passwordResetToken=passwordResetTokenService.getPasswordResetToken(token);
        User user=passwordResetToken.getUser();
        String newPassword=RandomPasswordGenerator.generate();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        passwordResetToken.setUsedToken(true);
        passwordResetTokenService.updatePasswordResetToken(passwordResetToken);

        OnPasswordResetSuccessEvent onPasswordResetSuccessEvent=new OnPasswordResetSuccessEvent(
                user.getEmail(),
                newPassword,
                locale
        );

        applicationEventPublisher.publishEvent(onPasswordResetSuccessEvent);
    }

    @Override
    public void changeLockStatus(Long id, ChangeUserLockStatusRequest changeUserLockStatusRequest, Authentication authentication) {
        UserDetailsImpl userDetails=(UserDetailsImpl) authentication.getPrincipal();
        User user=userValidator.checkUserExistById(id);


        if(userDetails.getId()==user.getId()){
            throw new AccessDeniedException("exception.CannotChangeAdminStatus");
        }else{
            if(!changeUserLockStatusRequest.getStatus()){
                authenticationTokenService.deleteTokensByUserId(id);
            }
            user.setNonLocked(changeUserLockStatusRequest.getStatus());
            userRepository.save(user);
        }

    }



    @Override
    public void updatePassword(String email, UpdatePasswordRequest updatePasswordRequest) {
        User user=userValidator.checkUserExistByEmail(email);

        if(!passwordEncoder.matches(updatePasswordRequest.getPassword(),user.getPassword())){
            throw new InvalidOldPasswordException("exception.InvalidOldPasswordException");
        }

        user.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));

        userRepository.save(user);
    }

    @Override
    public void updateEmail(Authentication authentication, EmailRequest emailRequest,Locale locale) {
        UserDetailsImpl userDetails=(UserDetailsImpl) authentication.getPrincipal();
        User user=userValidator.checkUserExistById(userDetails.getId());

        String oldEmail=user.getEmail();
        user.setEmail(emailRequest.getEmail());

        userRepository.save(user);

        OnEmailChangeEvent onEmailChangeEvent=new OnEmailChangeEvent(
                oldEmail,
                emailRequest.getEmail(),
                locale
        );

        applicationEventPublisher.publishEvent(onEmailChangeEvent);

    }

    @Override
    public UserResponse getUser(Long id) {
        User user=userValidator.checkUserExistById(id);
        UserResponse userResponse=modelMapper.map(user,UserResponse.class);

        return userResponse;
    }

    @Override
    public Page<UserResponse> getUsers(Optional<String> filter, Pageable pageable) {
        return userRepository.findAll(filter.orElse(""),pageable).map((user)->{
            return modelMapper.map(user, UserResponse.class);
        });
    }

    @Override
    public void updateUser(Long id,UserRequest userRequest,Locale locale) {
        User user=userValidator.checkUserExistById(id);

        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());


        if(!user.getEmail().equals(userRequest.getEmail())){
            if(!user.getEnabled()){
                userValidator.checkUserAlreadyExist(userRequest.getEmail());
                user.setEmail(userRequest.getEmail());
                userRepository.save(user);
                verificationTokenService.deleteVerificationTokensByUserId(user.getId());

                //send email

                VerificationTokenRequest verificationTokenRequest=new VerificationTokenRequest();
                verificationTokenRequest.setUsedToken(false);
                verificationTokenRequest.setUserEmail(user.getEmail());
                verificationTokenRequest.setExpiryDate(LocalDateTime.now().plusHours(24));


                String token=verificationTokenService.createVerificationToken(verificationTokenRequest);



                OnRegistrationCompleteEvent onRegistrationCompleteEvent=new OnRegistrationCompleteEvent(
                        user.getEmail(),
                        token,
                        locale
                );

                applicationEventPublisher.publishEvent(onRegistrationCompleteEvent);

            }else{
                throw new CannotChangeEnabledUserEmailException("exception.CannotChangeEnabledUserEmailException");
            }
        }else{
            userRepository.save(user);
        }

    }


}
