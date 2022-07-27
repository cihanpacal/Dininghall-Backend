package com.cihanpacal.dininghall.event;

import com.cihanpacal.dininghall.config.DiningHallProperties;
import com.cihanpacal.dininghall.error.EmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class EmailChangeListener implements ApplicationListener<OnEmailChangeEvent> {

    private final MessageSource messageSource;
    private final JavaMailSender javaMailSender;
    private final DiningHallProperties diningHallProperties;

    @Override
    public void onApplicationEvent(OnEmailChangeEvent event) {
        String emailAddress=event.getEmail();
        String newEmailAdress=event.getNewEmail();
        Locale locale=event.getLocale();

        String subject=messageSource.getMessage("message.EmailChangeSubject",null,locale);;
        String emailChangeMessage=messageSource.getMessage("message.EmailChangeMessage",new Object[]{newEmailAdress},locale);

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(emailAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(emailChangeMessage);
        simpleMailMessage.setFrom(diningHallProperties.getEmailFrom());

        try {
            javaMailSender.send(simpleMailMessage);
        }catch (RuntimeException ex){
            throw new EmailException("exception.EmailException");
        }
    }
}
