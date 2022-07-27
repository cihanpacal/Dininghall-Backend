package com.cihanpacal.dininghall.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnPasswordResetSuccessEvent extends ApplicationEvent {

    private String password;
    private String email;
    private Locale locale;

    public OnPasswordResetSuccessEvent(String email,String password,Locale locale) {
        super(email);
        this.email=email;
        this.password=password;
        this.locale=locale;
    }
}
