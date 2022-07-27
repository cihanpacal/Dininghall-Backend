package com.cihanpacal.dininghall;

import com.cihanpacal.dininghall.config.DiningHallProperties;
import com.cihanpacal.dininghall.error.EmailException;
import com.cihanpacal.dininghall.model.entity.Role;
import com.cihanpacal.dininghall.model.entity.User;
import com.cihanpacal.dininghall.repository.UserRepository;
import com.cihanpacal.dininghall.util.RandomPasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class CreateAdminCommandLineRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MessageSource messageSource;
    private final JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;
    private final DiningHallProperties diningHallProperties;


    @Override
    public void run(String... args) throws Exception {
            createAdminAccount();
    }

    private void createAdminAccount(){
        User user=new User();
        user.setFirstName(diningHallProperties.getAdmin().getFirstName());
        user.setLastName(diningHallProperties.getAdmin().getLastName());
        user.setEmail(diningHallProperties.getAdmin().getEmail());

        String adminPassword=diningHallProperties.getAdmin().getPassword();
        adminPassword=adminPassword.equals("random")?RandomPasswordGenerator.generate() : adminPassword;
        user.setPassword(passwordEncoder.encode(adminPassword));
        user.setRole(Role.ROLE_ADMIN);
        user.setEnabled(true);
        user.setNonLocked(true);
        userRepository.save(user);

        if(diningHallProperties.getAdmin().getSendPasswordToAdminEmail()){
            sendAccountInfoMailToAdmin(adminPassword);
        }else{
            System.out.println("Admin Username : "+diningHallProperties.getAdmin().getEmail());
            System.out.println("Admin Password : "+adminPassword);
        }

    }

    private void sendAccountInfoMailToAdmin(String adminPassword){

        Locale locale= Locale.forLanguageTag("tr_TR");

        String subject=messageSource.getMessage("message.AdminEmailSubject",null,locale);;
        String message=messageSource
                .getMessage("message.AdminEmailMessage",new Object[]{
                        diningHallProperties.getAdmin().getEmail(),
                        adminPassword
                },locale);

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(diningHallProperties.getAdmin().getEmail());
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
