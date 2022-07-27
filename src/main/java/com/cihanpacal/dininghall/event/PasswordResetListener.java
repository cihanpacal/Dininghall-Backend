package com.cihanpacal.dininghall.event;

import com.cihanpacal.dininghall.config.DiningHallProperties;
import com.cihanpacal.dininghall.error.EmailException;
import com.cihanpacal.dininghall.event.OnPasswordResetEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class PasswordResetListener implements ApplicationListener<OnPasswordResetEvent> {
    private final MessageSource messageSource;
    private final JavaMailSender javaMailSender;
    private final DiningHallProperties diningHallProperties;

    @Override
    public void onApplicationEvent(OnPasswordResetEvent event) {
        String mailAddress=event.getEmail();
        Locale locale=event.getLocale();
        String token= event.getToken();

        String subject=messageSource.getMessage("message.PasswordResetTokenEmailSubject",null,locale);;
        String passwordResetUrl= diningHallProperties.getClientAppInfo().getClientUrl() +
                diningHallProperties.getClientAppInfo().getPasswordResetUrl()
                +token;
        String message=messageSource.getMessage("message.PasswordResetTokenEmailMessage",new Object[]{passwordResetUrl},locale);

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(mailAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom(diningHallProperties.getEmailFrom());

        try {
            javaMailSender.send(simpleMailMessage);
        }catch (RuntimeException ex){
            throw new EmailException("exception.EmailException");
        }
    }
}
