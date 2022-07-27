package com.cihanpacal.dininghall.event;

import com.cihanpacal.dininghall.config.DiningHallProperties;
import com.cihanpacal.dininghall.error.EmailException;
import com.cihanpacal.dininghall.event.OnRegistrationCompleteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class RegisterationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final MessageSource messageSource;
    private final JavaMailSender javaMailSender;
    private final DiningHallProperties diningHallProperties;



    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        String emailAddress=event.getEmail();
        Locale locale=event.getLocale();
        String token=event.getToken();

        String subject=messageSource.getMessage("message.ConfirmationEmailSubject",null,locale);;
        String confirmationUrl=diningHallProperties.getClientAppInfo().getClientUrl()+
                diningHallProperties.getClientAppInfo().getConfirmationUrl()
                +token;
        String confirmationMessage=messageSource.getMessage("message.ConfirmationEmailMessage",new Object[]{confirmationUrl},locale);

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(emailAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(confirmationMessage);
        simpleMailMessage.setFrom(diningHallProperties.getEmailFrom());

        try {
            javaMailSender.send(simpleMailMessage);
        }catch (RuntimeException ex){
           throw new EmailException("exception.EmailException");
        }
    }

    
}
