package com.duynguyen.personal.personalproject.service;

import com.sendgrid.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    private final String USERNAME = System.getenv("SENDGRID_USERNAME");

    private final String API_KEY= System.getenv("SENDGRID_API_KEY");

    @Override
    public Response send(Email to, String subject, String message) {

        Email from = new Email(USERNAME);

        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(API_KEY);
        Request request = new Request();
        Response response = new Response();

        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            response = sg.api(request);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return response;
    }

}
