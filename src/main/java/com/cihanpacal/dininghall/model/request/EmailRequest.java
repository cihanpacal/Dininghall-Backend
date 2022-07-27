package com.cihanpacal.dininghall.model.request;

import com.cihanpacal.dininghall.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmailRequest {
    @ValidEmail
    @NotNull
    private String email;
}
