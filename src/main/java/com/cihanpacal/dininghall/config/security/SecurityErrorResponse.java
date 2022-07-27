package com.cihanpacal.dininghall.config.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class SecurityErrorResponse {
    private final String timestamp = LocalDateTime.now().toString();
    private String message;
    private Integer status;
    private String path;
    private Object errors;
}
