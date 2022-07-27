package com.cihanpacal.dininghall.event;

import com.cihanpacal.dininghall.config.DiningHallProperties;
import com.cihanpacal.dininghall.error.EmailException;
import com.cihanpacal.dininghall.event.OnPasswordResetSuccessEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class PasswordResetSuccessListener implements ApplicationListener<OnPasswordResetSuccessEvent> {

    private final MessageSource messageSource;
    private final JavaMailSender javaMailSender;
    private final DiningHallProperties diningHallProperties;

    @Override
    public void onApplicationEvent(OnPasswordResetSuccessEvent event) {
        String emailAddress=event.getEmail();
        String password=event.getPassword();
        Locale locale=event.getLocale();

        String subject=messageSource.getMessage("message.PasswordResetSuccesEmailSubject",null,locale);;
        String passwordResetSuccessMessage=messageSource
                .getMessage("message.PasswordResetSuccesEmailMessage",new Object[]{emailAddress,password},locale);

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(emailAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(passwordResetSuccessMessage);
        simpleMailMessage.setFrom(diningHallProperties.getEmailFrom());

        try {
            javaMailSender.send(simpleMailMessage);
        }catch (RuntimeException ex){
            throw new EmailException("exception.EmailException");
        }

    }
}
