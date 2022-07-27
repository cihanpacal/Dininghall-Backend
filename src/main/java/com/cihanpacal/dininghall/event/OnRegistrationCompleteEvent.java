package com.cihanpacal.dininghall.event;

import com.cihanpacal.dininghall.model.entity.User;
import lombok.*;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private Locale locale;
    private String email;
    private String token;

    public OnRegistrationCompleteEvent(String email,String token,Locale locale) {
        super(email);
        this.email=email;
        this.token=token;
        this.locale=locale;
    }
}
