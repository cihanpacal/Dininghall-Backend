package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.ProductGroup;
import com.cihanpacal.dininghall.model.entity.UserLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface UserLogRepository extends JpaRepository<UserLog,Long> {

    @Query("SELECT ul FROM UserLog ul WHERE" +
            "( ul.username LIKE %:filter% " +
            "or ul.url LIKE %:filter% or ul.ipAddress LIKE %:filter% " +
            "or ul.httpMethodName LIKE %:filter%) " +
            "and (:startDateTime IS NULL or ul.time>=:startDateTime) " +
            "and (:endDateTime IS NULL or ul.time<=:endDateTime)")
    Page<UserLog> findAll(
            @Param("filter") String filter,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            Pageable pageable
    );
}
