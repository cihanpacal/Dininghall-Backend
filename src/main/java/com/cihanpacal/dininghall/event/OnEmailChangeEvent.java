package com.cihanpacal.dininghall.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnEmailChangeEvent extends ApplicationEvent {

    private String email;
    private String newEmail;
    private Locale locale;

    public OnEmailChangeEvent(String email, String newEmail, Locale locale) {
        super(email);
        this.email=email;
        this.newEmail=newEmail;
        this.locale=locale;
    }
}
