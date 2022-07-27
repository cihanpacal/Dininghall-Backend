package com.cihanpacal.dininghall.controller;

import com.cihanpacal.dininghall.model.response.FoodGroupResponse;
import com.cihanpacal.dininghall.model.response.UserLogResponse;
import com.cihanpacal.dininghall.service.UserLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/user-logs")
@RequiredArgsConstructor
public class UserLogController {

    private final UserLogService userLogService;

    @GetMapping
    public ResponseEntity<Page<UserLogResponse>> getUserLogs(
            @RequestParam Optional<String> filter,
            @RequestParam Optional<String> startTime,
            @RequestParam Optional<String> endTime,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<UserLogResponse> userLogResponsePage=userLogService
                .getUserLogs(
                        filter,
                        startTime,
                        endTime,
                        pageable
                );


        return  ResponseEntity.ok(userLogResponsePage);
    }
}
