package com.duynguyen.personal.personalproject.event;

import com.duynguyen.personal.personalproject.domain.MyUser;
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

    @Async
    @EventListener
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        MyUser myUser = event.getUser();
        String token = UUID.randomUUID().toString();
        System.out.println(token);
        tokenService.create(myUser, token);

        String recipientAddress = myUser.getEmail();
        String subject = "Registration Confirmation";
        System.out.println(recipientAddress);

        String confirmationUrl = event.getAppUrl() + "/registration/confirm?token=" + token;
        String message = "Activate your account by clicking in this link: ";

        Email from = new Email(System.getenv("SENDGRID_USERNAME"));
        Email to = new Email(myUser.getEmail());
        Content content = new Content("text/plain", message + confirmationUrl);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            Response response = sg.api(request);
            System.out.println(response.statusCode);
            System.out.println(response.body);
            System.out.println(response.headers);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
