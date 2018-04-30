package com.duynguyen.personal.personalproject.event;

import com.duynguyen.personal.personalproject.domain.MyUser;
import com.duynguyen.personal.personalproject.service.MyUserService;
import com.duynguyen.personal.personalproject.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
public class RegistrationListener {

    @Autowired
    private MyUserService myUserService;

    @Autowired
    private JavaMailSender mailSender;

    @Resource
    private VerificationTokenService tokenService;

    @Async
    @EventListener
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MyUser myUser = event.getUser();
        String token = UUID.randomUUID().toString();
        System.out.println(token);
        tokenService.create(myUser, token);

        String recipientAddress = myUser.getEmail();
        String subject = "Registration Confirmation";
        System.out.println(recipientAddress);

        String confirmationUrl = event.getAppUrl() + "/registration/confirm?token=" + token;
        String message = "Activate your account by clicking in this link: ";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + confirmationUrl);
        mailSender.send(email);
    }

}
