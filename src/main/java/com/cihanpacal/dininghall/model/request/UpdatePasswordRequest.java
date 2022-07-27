package com.cihanpacal.dininghall.model.request;


import com.cihanpacal.dininghall.validation.PasswordMatches;
import com.cihanpacal.dininghall.validation.ValidPassword;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@PasswordMatches
public class UpdatePasswordRequest {

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    @ValidPassword
    private String newPassword;

    @NotNull
    @NotEmpty
    private String newPasswordRepeat;

}
