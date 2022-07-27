package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.response.ProductGroupResponse;
import com.cihanpacal.dininghall.model.response.UserLogResponse;
import com.cihanpacal.dininghall.repository.UserLogRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserLogServiceImpl implements UserLogService{

    private final UserLogRepository userLogRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<UserLogResponse> getUserLogs(
            Optional<String> filter,
            Optional<String> optionalStartDateTime,
            Optional<String> optionalEndDateTime,
            Pageable pageable
    ) {

        LocalDateTime startDateTime= LocalDateTime.parse(optionalStartDateTime.orElse("1970-01-01T"+ LocalTime.MIN.toString()));
        LocalDateTime endDateTime=LocalDateTime.parse(optionalEndDateTime.orElse("2100-01-01T"+LocalTime.MAX.toString()));

        return userLogRepository.findAll(filter.orElse(""),startDateTime,endDateTime,pageable).map((userLog)->{
            return modelMapper.map(userLog, UserLogResponse.class);
        });
    }
}
