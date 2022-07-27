package com.cihanpacal.dininghall.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnConfirmationSuccessEvent extends ApplicationEvent {

    private String password;
    private String email;
    private Locale locale;

    public OnConfirmationSuccessEvent(String email,String password,Locale locale) {
        super(email);
        this.password=password;
        this.email=email;
        this.locale=locale;
    }
}
