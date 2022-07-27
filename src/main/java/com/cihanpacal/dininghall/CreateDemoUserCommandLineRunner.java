package com.cihanpacal.dininghall;

import com.cihanpacal.dininghall.config.DiningHallProperties;
import com.cihanpacal.dininghall.model.entity.Role;
import com.cihanpacal.dininghall.model.entity.User;
import com.cihanpacal.dininghall.repository.UserRepository;
import com.cihanpacal.dininghall.util.RandomPasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile(value="default | dev")
public class CreateDemoUserCommandLineRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DiningHallProperties diningHallProperties;



    @Override
    public void run(String... args) throws Exception {

        User user=new User();
        user.setFirstName(diningHallProperties.getDemoUser().getFirstName());
        user.setLastName(diningHallProperties.getDemoUser().getLastName());
        user.setEmail(diningHallProperties.getDemoUser().getEmail());
        String userPassword=diningHallProperties.getDemoUser().getPassword();
        userPassword=userPassword.equals("random")?RandomPasswordGenerator.generate() : userPassword;
        user.setPassword(passwordEncoder.encode(userPassword));
        user.setRole(Role.ROLE_USER);
        user.setEnabled(true);
        user.setNonLocked(true);
        userRepository.save(user);

        System.out.println("Demo User Username : "+diningHallProperties.getDemoUser().getEmail());
        System.out.println("Demo User Password : "+userPassword);

    }
}
