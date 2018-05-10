package com.duynguyen.personal.personalproject.event;

import com.duynguyen.personal.personalproject.domain.MyUser;
import com.duynguyen.personal.personalproject.service.EmailService;
import com.duynguyen.personal.personalproject.service.MyUserService;
import com.duynguyen.personal.personalproject.service.VerificationTokenService;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

@Component
public class RegistrationListener {

    @Resource
    private VerificationTokenService tokenService;

    @Resource
    private EmailService emailService;

    @Async
    @EventListener
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        MyUser myUser = event.getUser();
        String token = UUID.randomUUID().toString();
        tokenService.create(myUser, token);

        String subject = "Registration Confirmation";

        String confirmationUrl = event.getAppUrl() + "/registration/confirm?token=" + token;
        String message = "Activate your account by clicking in this link: " + confirmationUrl;

        emailService.send(new Email(myUser.getEmail()), subject, message);
    }

}
