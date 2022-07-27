package com.cihanpacal.dininghall.event;

import com.cihanpacal.dininghall.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnPasswordResetEvent extends ApplicationEvent {

    private String email;
    private String token;
    private Locale locale;

    public OnPasswordResetEvent(String email,String token,Locale locale) {
        super(email);
        this.email=email;
        this.token=token;
        this.locale=locale;
    }
}
