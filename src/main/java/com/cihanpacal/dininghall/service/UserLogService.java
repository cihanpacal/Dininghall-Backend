package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.response.UserLogResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserLogService {
    Page<UserLogResponse> getUserLogs(
            Optional<String> filter,
            Optional<String> optionalStartDateTime,
            Optional<String> optionalEndDateTime,
            Pageable pageable);
}
