package com.cihanpacal.dininghall.config;

import com.cihanpacal.dininghall.validation.ValidEmail;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "dininghall")
@Getter
@Setter
public class DiningHallProperties {

    private Admin admin=new Admin();
    private User demoUser=new User();
    private ClientAppInfo clientAppInfo=new ClientAppInfo();
    private String emailFrom;

    @Getter
    @Setter
    public static class Admin{
        @ValidEmail
        private String email="admin@admin.com";
        private String firstName="admin";
        private String lastName="admin";
        private String password="admin";
        private Boolean sendPasswordToAdminEmail=false;
    }

    @Getter
    @Setter
    public static class User{

        @ValidEmail
        private String email="user@user.com";
        private String firstName="user";
        private String lastName="user";
        private String password="user";

    }

    @Getter
    @Setter
    public static class ClientAppInfo {
        private String clientUrl="http://localhost:4200";
        private String passwordResetUrl="/account/reset-password/";
        private String confirmationUrl="/account/confirm-account/";
    }

}
