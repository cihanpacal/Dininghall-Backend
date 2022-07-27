package com.cihanpacal.dininghall.controller;

import com.cihanpacal.dininghall.config.security.UserDetailsImpl;
import com.cihanpacal.dininghall.model.request.EmailRequest;
import com.cihanpacal.dininghall.model.request.UpdatePasswordRequest;
import com.cihanpacal.dininghall.model.response.UserResponse;
import com.cihanpacal.dininghall.service.AuthenticationTokenService;
import com.cihanpacal.dininghall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Locale;

@RestController
@RequestMapping("/api/1.0/account")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<UserResponse> getMyAccount(Authentication authentication){
        UserDetailsImpl userDetails= (UserDetailsImpl) authentication.getPrincipal();
        UserResponse userResponse=userService.getUser(userDetails.getId());
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("confirm/{token}")
    public ResponseEntity<?> confirmAccount(@PathVariable String token,Locale locale){

        userService.confirmAccount(token,locale);

        return ResponseEntity.ok().build();
    }

    @PostMapping("forget-password")
    public  ResponseEntity<?> resetPassword(@RequestBody @Valid EmailRequest emailRequest,Locale locale){
        userService.createPasswordResetToken(emailRequest,locale);

        return ResponseEntity.ok().build();
    }

    @PutMapping("reset-password/{token}")
    public ResponseEntity<?> sendNewPassword(@PathVariable String token,Locale locale){
        userService.sendNewPassword(token,locale);

        return ResponseEntity.ok().build();
    }



    @PutMapping("update-password")
    public ResponseEntity<?> updatePassword(Authentication authentication,@RequestBody @Valid UpdatePasswordRequest updatePasswordRequest){
        userService.updatePassword(authentication.getName(),updatePasswordRequest);

        return ResponseEntity.ok().build();
    }

    @PutMapping("update-email")
    public ResponseEntity<?> updateEmail(
            Authentication authentication,
            @RequestBody @Valid EmailRequest emailRequest,
            Locale locale
    ){
        userService.updateEmail(authentication,emailRequest,locale);

        return  ResponseEntity.ok().build();
    }




}
