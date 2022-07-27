package com.cihanpacal.dininghall.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChangeUserLockStatusRequest {
    @NotNull
    private Boolean status;
}
