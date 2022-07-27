package com.cihanpacal.dininghall.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLogResponse {

    private String username;

    private String ipAddress;

    private String httpMethodName;

    private String url;

    private LocalDateTime time;

    private Integer statusCode;
}
