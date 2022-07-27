package com.cihanpacal.dininghall.model.request;

import com.cihanpacal.dininghall.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class PasswordResetTokenRequest {
    @NotNull
    private boolean usedToken;

    @NotNull
    @Future
    private LocalDateTime expiryDate;

    @NotNull
    @ValidEmail
    private String userEmail;
}
