package com.cihanpacal.dininghall.controller;


import com.cihanpacal.dininghall.model.request.ChangeUserLockStatusRequest;
import com.cihanpacal.dininghall.model.request.UserRequest;
import com.cihanpacal.dininghall.model.request.EmailRequest;
import com.cihanpacal.dininghall.model.response.FoodGroupResponse;
import com.cihanpacal.dininghall.model.response.StockResponse;
import com.cihanpacal.dininghall.model.response.UserResponse;
import com.cihanpacal.dininghall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody  @Valid UserRequest createUserRequest, Locale locale){
        Long id=userService.createUser(createUserRequest,locale);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        UserResponse userResponse=userService.getUser(id);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getUsers(
            @RequestParam Optional<String> filter,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<UserResponse> userResponsePage=userService
                .getUsers(filter,pageable);


        return  ResponseEntity.ok(userResponsePage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody @Valid UserRequest userRequest,Locale locale){
        userService.updateUser(id,userRequest,locale);

        return ResponseEntity.ok().build();
    }

    @PutMapping("change-lock-status/{id}")
    public ResponseEntity<?> changeLockStatus(
            Authentication authentication,
            @PathVariable Long id,
            @RequestBody @Valid ChangeUserLockStatusRequest changeUserLockStatusRequest
    ){
        userService.changeLockStatus(id,changeUserLockStatusRequest,authentication);
        return ResponseEntity.ok().build();
    }

    @PostMapping("resend-verification-token")
    public ResponseEntity<?> resendVerificationToken(@RequestBody @Valid EmailRequest emailRequest, Locale local){
        userService.resendVerificationToken(emailRequest,local);
        return ResponseEntity.ok().build();
    }

}
